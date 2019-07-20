function getCategories() {
    var categories = [];
    $.each($("input[name='category']:checked"), function(){
        categories.push($(this).val());
    });
    var input = $('#categories_input');
    input.val(categories.join(", "));
}
function getCountries() {
    var countries = [];
    $.each($("input[name='country']:checked"), function(){
        countries.push($(this).val());
    });
    var input = $('#countries_input');
    input.val(countries.join(", "));
}