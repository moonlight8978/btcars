(function () {
    'use strict';

    angular
        .module('btcarsApp')
        .factory('CartService', CartService);

    CartService.$inject = ['$localStorage', 'Customer', 'getCarFactory'];

    function CartService($localStorage, Customer, getCarFactory) {
        var cart = {};
        cart.deleteItem = deleteItem;
        cart.addItem = addItem;

        function deleteItem(item) {
            var index = $localStorage.customer.carts.indexOf(item);
            $localStorage.customer.carts.splice(index, 1);
            Customer.update($localStorage.customer);
            $localStorage.total = getCarFactory.calTotalPrice($localStorage.customer.carts);
            $localStorage.totalFix = $localStorage.total.toLocaleString();
            alert('Deleted!');
        }

        function addItem(item) {
            var length = $localStorage.customer.carts.length;
            var i=0;
            if (length > 0) {
                for (i=0; i<length; i++) {
                    if (item.id == $localStorage.customer.carts[i].id)
                        break;
                }
            }
            if (i == length) {
                $localStorage.customer.carts.push(item);
                Customer.update($localStorage.customer);
                $localStorage.total = getCarFactory.calTotalPrice($localStorage.customer.carts);
                $localStorage.totalFix = $localStorage.total.toLocaleString();
                alert('Added!');
            } else {
                alert('Item is already in your cart!');
            }
        }
        return cart;
    }

})();
