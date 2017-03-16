'use strict';

var App = angular.module('myApp', []);
App.factory('ProduitService', [ '$http', '$q', function($http, $q) {
	var urlWS = "http://localhost:8088/rest/";
	return {

		fetchAllProduits : function() {
			return $http.get(urlWS).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while fetching produits');
				return $q.reject(errResponse);
			});
		},

		createProduit : function(produit) {
			return $http.post(urlWS, produit).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while creating produit');
				return $q.reject(errResponse);
			});
		},

		updateProduit : function(produit, id) {
			return $http.put(urlWS + '' + id, produit).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while updating produit');
				return $q.reject(errResponse);
			});
		},

		deleteProduit : function(id) {
			return $http['delete'](urlWS + '' + id).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while deleting produit');
				return $q.reject(errResponse);
			});
		},

		deleteAllProduits : function() {
			return $http['delete'](urlWS).then(function(response) {
				return response.data;
			}, function(errResponse) {
				console.error('Error while deleting all produits');
				return $q.reject(errResponse);
			});
		}

	};

} ]);