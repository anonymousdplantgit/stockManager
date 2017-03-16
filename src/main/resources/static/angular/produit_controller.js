'use strict';

App.controller('ProduitController', [
		'$scope',
		'ProduitService',
		function($scope, ProduitService) {
			var self = this;
			self.commande = {
				commandeId : null,
				dateCmd : '',
				valide : '',
				commandeProduits : []
				
			};
			self.tvshows = [];

			self.fetchAllProduits = function() {
				ProduitService.fetchAllProduits().then(

				function(d) {
					self.tvshows = d;
					console.log('fetching all tvshows');
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};

			self.createProduit = function(tvshow) {
				ProduitService.createProduit(tvshow).then(self.fetchAllProduits,
						function(errResponse) {
							console.error('Error while creating Produit.');
						});
			};

			self.updateProduit = function(tvshow, id) {
				ProduitService.updateProduit(tvshow, id).then(
						self.fetchAllProduits, function(errResponse) {
							console.error('Error while updating Produit.');
						});
			};

			self.deleteProduit = function(id) {
				ProduitService.deleteProduit(id).then(self.fetchAllProduits,
						function(errResponse) {
							console.error('Error while deleting Produit.');
						});
			};

			self.deleteAllProduits = function() {
				ProduitService.deleteAllProduits().then(self.fetchAllProduits,
						function(errResponse) {
							console.error('Error while deleting Produits.');
						});
			};

			self.fetchAllProduits();

			self.submit = function() {
				if (self.tvshow.id == null) {
					console.log('Saving New Produit', self.tvshow);
					self.createProduit(self.tvshow);
				} else {
					self.updateProduit(self.tvshow, self.tvshow.id);
					console.log('Produit updated with id ', self.tvshow.id);
				}
				self.reset();
			};

			self.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < self.tvshows.length; i++) {
					if (self.tvshows[i].id == id) {
						self.tvshow = angular.copy(self.tvshows[i]);
						break;
					}
				}
			};

			self.remove = function(id) {
				console.log('id to be deleted', id);
				for (var i = 0; i < self.tvshows.length; i++) {// clean form if
																// the tvshow to
																// be deleted is
																// shown there.
					if (self.tvshows[i].id == id) {
						self.reset();
						break;
					}
				}
				self.deleteProduit(id);
			};

			self.removeAll = function() {
				console.log('Removing all');
				self.reset();
				self.deleteAllProduits();
			};

			self.reset = function() {
				self.tvshow = {
					id : null,
					label : '',
					season : '',
					episode : ''
				};
				$scope.myForm.$setPristine(); // reset Form
			};

		} ]);