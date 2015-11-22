/*global angular */

/**
 * Services that persists and retrieves todos from localStorage or a backend API
 * if available.
 *
 * They both follow the same API, returning promises for all changes to the
 * model.
 */
angular.module('todomvc')
	.factory('todoStorage', function ($http, $injector) {
		'use strict';

		// Detect if an API backend is present. If so, return the API module, else
		// hand off the localStorage adapter
		return $injector.get('api');
	})

	.factory('api', function ($resource, $http) {
		'use strict';

		function getIdFor(todo){
			var href = todo._links.self.href;
			return href.substr(href.lastIndexOf('/') + 1)
		}

		var store = {
			todos: [],

			api: $resource('/todo/:id', null,
				{
					update: { 
						method:'PUT'
					},
					query: {
						method: 'GET',
						isArray: true,
						transformResponse: function(data, headersGetter){
							var returnData = [];
							data  = JSON.parse(data);
							if(! data._embedded){
								return returnData;
							}
							data._embedded.beanResources.forEach(function(todo){

								todo.id = getIdFor(todo);
								returnData.push(todo);
							});
							return returnData;
						}
					}
				}
			),
			setToken: function(token){
				$http.defaults.headers.common.Authorization = 'Bearer '+token;
			},

			clearCompleted: function () {
				var originalTodos = store.todos.slice(0);

				var incompleteTodos = store.todos.filter(function (todo) {
					return !todo.completed;
				});
				angular.copy(incompleteTodos, store.todos);

				originalTodos.filter(function (todo) {
					return todo.completed;
				}).forEach(function(completedTask){
					store.api.delete({ id: completedTask.id },
					function () {
					}, function error() {
						angular.copy(originalTodos, store.todos);
					});
				});
			},

			delete: function (todo) {
				var originalTodos = store.todos.slice(0);

				store.todos.splice(store.todos.indexOf(todo), 1);
				return store.api.delete({ id: todo.id },
					function () {
					}, function error() {
						angular.copy(originalTodos, store.todos);
					});
			},

			get: function () {
				return store.api.query(function (resp) {
					angular.copy(resp, store.todos);
				});
			},

			insert: function (todo) {
				var originalTodos = store.todos.slice(0);

				return store.api.save(todo,
					function success(resp) {
						todo.id = getIdFor(resp);
						store.todos.push(todo);
					}, function error() {
						angular.copy(originalTodos, store.todos);
					})
					.$promise;
			},

			put: function (todo) {
				return store.api.update({ id: todo.id }, todo)
					.$promise;
			}
		};

		return store;
	});
