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
function confirmMovieFromListRemoval(movieTitle, movieId, listName) {
    var mod_movieName = $('#movieRemovalModal_movieName');
    var mod_movieId = $('#movieRemovalModal_movieId');
    var mod_listName = $('#movieRemovalModal_listName');
    var mod_hiddenListName = $('#movieRemovalModal_hiddenListName');
    mod_movieName.text(movieTitle);
    mod_movieId.val(movieId);
    mod_listName.text(listName);
    mod_hiddenListName.val(listName)
    $('#movieFromListRemovalModal').modal('show');
}
function confirmListRemoval(listName){
    var mod_listName = $('#listRemovalModal_listName');
    var mod_hiddenListName = $('#listRemovalModal_hiddenListName');
    mod_listName.text(listName);
    mod_hiddenListName.val(listName);
    $('#listRemovalModal').modal('show');
}
function showAllMoviesInProfile(){
    $.each($("div[name='prof_optional']"), function(){
        this.style.display = 'block';
    });
    $('#showAllButton').hide();
    $('#hideAllButton').show();
}
function hideAllMoviesInProfile() {
    $.each($("div[name='prof_optional']"), function(){
        this.style.setProperty('display', 'none', 'important');
    });
    $('#showAllButton').show();
    $('#hideAllButton').hide();
}