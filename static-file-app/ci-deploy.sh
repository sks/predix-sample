APP=todo-client
HOSTNAME=$APP

function status(){
    echo $(date +"%m-%d-%y %H:%M:%S ") $*
}


function exit_if_error(){
    if [ $1 -ne 0 ]
    then
        shift
        status "[ERROR] : "$*
        exit 1;
    fi
}

function get_options(){
	while getopts "b:h:" opt; do
	  case $opt in
	  	b)
			BUILD_NUMBER=$OPTARG
		;;
		h)
			HOSTNAME=$APP-$OPTARG
		;;
		\?) echo "Invalid option -$OPTARG"
	    ;;
	  esac
	done
}

function validateOptions(){

	if [ ${#BUILD_NUMBER} -eq 0 ]
		then
		exit_if_error 1 "BUILD_NUMBER not set using -b option"
	fi

	APP_NAME=$APP-$BUILD_NUMBER
	status "[INFO] Pushing the app with the hostname set as $HOSTNAME"
}

function delete_older_apps(){
	for app in $(cf a | grep $APP| awk '{print $1}')
	do
		if [ $app != $APP_NAME ]
			then
			status "Deleting the app $app"
			cf delete $app -f
			exit_if_error $? "Error deleting the older app $app"
		fi
	done
}

function cleanup(){
	echo "[INFO] deleting orphaned orphans if any"
	cf delete-orphaned-routes -f
	exit_if_error $? "Error cleaning up the unused routes"
}


function main(){
	get_options $*
	validateOptions
	status "[INFO] pushing the app : $APP_NAME"
	cf push $APP_NAME
	exit_if_error $? "Could not push the application as expected"
	cf map-route $APP_NAME run.aws-usw02-pr.ice.predix.io -n $HOSTNAME
	exit_if_error $? "Could not map the route $HOSTNAME to the app $APP_NAME"
	delete_older_apps
	cleanup
}

main $*