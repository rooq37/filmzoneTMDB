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
var directorId = 1;
function addDirector() {
    var newElementId = 'movie_director_new_' + directorId;
    var html =    '<div class="form-group col-md-5">'
                + '<input name="movie_directors_name" type="text" class="form-control" placeholder="Imię/Imiona">'
                + '</div>'
                + '<div class="form-group col-md-6">'
                + '<input name="movie_directors_surname" type="text" class="form-control" placeholder="Nazwisko">'
                + '</div>'
                + '<div class="form-group col-md-1">'
                + '<button onclick="removeElement(\'' + newElementId + '\')" '
                + 'type="button" class="close text-danger mt-auto text-right" aria-label="Remove" data-toggle="tooltip" data-placement="bottom" title="Usuń">'
                + '<span aria-hidden="true">&times;</span></button></div>';
    var parent = document.getElementById("movie_directors");
    var newElement = document.createElement("div");
    newElement.id = newElementId;
    newElement.setAttribute("class", "row");
    newElement.innerHTML = html;
    parent.appendChild(newElement);
    directorId++;
}
var scenarioId = 1;
function addScenario() {
    var newElementId = 'movie_scenario_new_' + scenarioId;
    var html = '<div class="form-group col-md-5"><input name="movie_scenario_name" type="text" class="form-control" placeholder="Imię/Imiona"></div>'
             + '<div class="form-group col-md-6"><input name="movie_scenario_surname" type="text" class="form-control" placeholder="Nazwisko"></div>'
             + '<div class="form-group col-md-1">'
             + '<button onclick="removeElement(\'' + newElementId + '\')" '
             + 'type="button" class="close text-danger mt-auto text-right" aria-label="Remove" data-toggle="tooltip" data-placement="bottom" title="Usuń">'
             + '<span aria-hidden="true">&times;</span></button></div>';
    var parent = document.getElementById("movie_scenarios");
    var newElement = document.createElement("div");
    newElement.id = newElementId;
    newElement.setAttribute("class", "row");
    newElement.innerHTML = html;
    parent.appendChild(newElement);
    scenarioId++;
}
var countryId = 1;
function addCountry() {
    var newElementId = 'movie_country_new_' + countryId;
    var html = '<div class="form-group col-md-6"><input name="movie_country" type="text" class="form-control" placeholder="Nazwa kraju"></div>'
             + '<div class="form-group col-md-1">'
             + '<button onclick="removeElement(\'' + newElementId + '\')" '
             + 'type="button" class="close text-danger mt-auto text-right" aria-label="Remove" data-toggle="tooltip" data-placement="bottom" title="Usuń">'
             + '<span aria-hidden="true">&times;</span></button></div>';
    var parent = document.getElementById("movie_countries");
    var newElement = document.createElement("div");
    newElement.id = newElementId;
    newElement.setAttribute("class", "row");
    newElement.innerHTML = html;
    parent.appendChild(newElement);
    countryId++;
}
var pictureId = 1;
function addPicture() {
    var newElementId = 'movie_picture_new_' + countryId;
    var html = '<div class="form-group col-md-5"><div class="custom-file mt-1">'
             + '<input name="picture_file" type="file" class="custom-file-input" id="movie_picture_input_' + pictureId + '" '
             + 'onchange="$(this).next().after().text($(this).val().split(\'\\\\\').slice(-1)[0])">'
             + '<label class="custom-file-label text-truncate" for="movie_picture_input_' + pictureId + '">Wybierz plik zdjęcia</label>'
             + '</div></div>'
             + '<div class="form-group col-md-6"><input name="picture_author" type="text" class="form-control my-1" placeholder="Źródło/Nazwa studia filmowego"></div>'
             + '<div class="form-group col-md-1">'
             + '<button onclick="removeElement(\'' + newElementId + '\')" '
             + 'type="button" class="close text-danger mt-auto text-right" aria-label="Remove" data-toggle="tooltip" data-placement="bottom" title="Usuń">'
             + '<span aria-hidden="true">&times;</span></button></div>';
    var parent = document.getElementById("movie_pictures");
    var newElement = document.createElement("div");
    newElement.id = newElementId;
    newElement.setAttribute("class", "row");
    newElement.innerHTML = html;
    parent.appendChild(newElement);
    pictureId++;
}
var castId = 1;
function addCast() {
    var newElementId = 'movie_character_new_' + castId;
    var html = '<div class="form-group col-md-3"><input name="movie_cast_actor_name" type="text" class="form-control" placeholder="Imię/Imiona aktora"></div>'
             + '<div class="form-group col-md-3"><input name="movie_cast_actor_surname" type="text" class="form-control" placeholder="Nazwisko aktora"></div>'
             + '<div class="form-group col-md-5"><input name="movie_cast_actor_role" type="text" class="form-control" placeholder="Rola"></div>'
             + '<div class="form-group col-md-1">'
             + '<button onclick="removeElement(\'' + newElementId + '\')" '
             + 'type="button" class="close text-danger mt-auto text-right" aria-label="Remove" data-toggle="tooltip" data-placement="bottom" title="Usuń">'
             + '<span aria-hidden="true">&times;</span></button></div>';
    var parent = document.getElementById("movie_cast");
    var newElement = document.createElement("div");
    newElement.id = newElementId;
    newElement.setAttribute("class", "row");
    newElement.innerHTML = html;
    parent.appendChild(newElement);
    castId++;
}
function changePictureInModal(pictureSrc) {
    document.getElementById("movie_image_preview").src = pictureSrc;
    $('#imagePreview').modal('show');
}
function removeElement(elementId) {
    var element = document.getElementById(elementId);
    element.parentNode.removeChild(element);
}
function confirmCommentRemoval(username, commentContent, commentId){
    var mod_commentId = $('#commentRemovalModal_commentId');
    var mod_username = $('#commentRemovalModal_username');
    var mod_content = $('#commentRemovalModal_content');
    mod_commentId.val(commentId);
    mod_username.text(username);
    mod_content.text(commentContent);
    $('#commentRemovalModal').modal('show');
}
function blockUser(username){
    var mod_username = $('#blockUserModal_username');
    mod_username.text(username);
    $('#blockUserModal').modal('show');
}