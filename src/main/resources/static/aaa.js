


$(document).ready(function (){
    $('.table .eBtn').on('click', function (event){
        event.preventDefault();
        var href = $(this).attr('href');

        $.get(href, function(user, status){
            $('.myForm #id').val(user.getId());
            $('.myForm #name').val(user.getName());
            $('.myForm #surname').val(user.getSurname());
            $('.myForm #age').val(user.getAge());
            $('.myForm #email').val(user.getEmail());
        });
        $('.myForm #editUser').modal();
    });
});