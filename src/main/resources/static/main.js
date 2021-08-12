const allUsers = document.querySelector('.tbody');
const singleUser = document.querySelector('.tbodyUser')
const addForm = document.querySelector('.addUser');
const deleteModal = document.querySelector('.modalDelete');
const editModal = document.querySelector('.modalEdit');
let outputAllUsers = '';
let outputSingleUser = '';

const url = 'http://localhost:8080/api/users';

// MAIN TABLE
const renderMainTable = (users) => {
    users.forEach(user => {
        outputAllUsers += `
                <tr data-id="${user.id}" id="row-user-${user.id}">
                    <td class="main-id" id="main-id-${user.id}">${user.id}</td>
                    <td class="main-name" id="main-name-${user.id}">${user.name}</td>
                    <td class="main-surname" id="main-surname-${user.id}">${user.surname}</td>
                    <td class="main-age" id="main-age-${user.id}">${user.age}</td>
                    <td class="main-email" id="main-email-${user.id}">${user.email}</td>
                    <td class="main-roles" id="main-roles-${user.id}">
            `;
        user.roles.forEach(role => {
            outputAllUsers += `
                ${role.name.substring(5)}
            `;
        })
        outputAllUsers += `
                    </td>        
                    <td>
                        <button type="button" class="btn btn-info btn-sm"
                                data-toggle="modal" id="editButton" data-target="#modal-edit">Edit</button>
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm"
                                data-toggle="modal" id="deleteButton" data-target="#modal-delete">Delete</button>
                    </td>
                </tr>
            `;
    });
    allUsers.innerHTML = outputAllUsers;
}
// USER TABLE
const renderUserTable = (user) => {
    outputSingleUser += `
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>
    `;
    user.roles.forEach(role => {
        outputSingleUser += `
            ${role.name.substring(5)}
        `;
    })
    outputSingleUser += `
            </td>
        </tr>
    `;
    singleUser.innerHTML = outputSingleUser;
};

// GET - get logged user
fetch(`${url}/logged`)
    .then(res => res.json())
    .then(user => renderUserTable(user))

// GET - get all users
fetch(url)
    .then(res => res.json())
    .then(users => {
        return renderMainTable(users)
    })

// UPDATE/DELETE USERS
let deleteId
let editId
allUsers.addEventListener('click', (e) => {
    e.preventDefault();
    const parent = e.target.parentElement.parentElement;

    let deleteButtonIsPressed = e.target.id === 'deleteButton'
    let editButtonIsPressed = e.target.id === 'editButton'

    let currentUserId = parent.querySelector('.main-id').textContent
    let currentUserName = parent.querySelector('.main-name').textContent
    let currentUserSurname = parent.querySelector('.main-surname').textContent
    let currentUserAge = parent.querySelector('.main-age').textContent
    let currentUserEmail = parent.querySelector('.main-email').textContent

    if (deleteButtonIsPressed) {
        deleteId = currentUserId
        $('#idDelete').val(currentUserId);
        $('#nameDelete').val(currentUserName);
        $('#surnameDelete').val(currentUserSurname);
        $('#ageDelete').val(currentUserAge);
        $('#emailDelete').val(currentUserEmail);
    }

    if (editButtonIsPressed) {
        editId = currentUserId
        $('#idEdit').val(currentUserId);
        $('#nameEdit').val(currentUserName);
        $('#surnameEdit').val(currentUserSurname);
        $('#ageEdit').val(currentUserAge);
        $('#emailEdit').val(currentUserEmail);
        $('#passwordEdit').val('');
        $('#rolesEdit').val('');
    }


})

// UPDATE - update user
editModal.addEventListener('submit', (e) => {
    e.preventDefault();
        fetch(`${url}/${editId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: $('#idEdit').val(),
                name: $('#nameEdit').val(),
                surname: $('#surnameEdit').val(),
                age: $('#ageEdit').val(),
                email: $('#emailEdit').val(),
                password: $('#passwordEdit').val(),
                roles: $('#rolesEdit').val()
            })
        })
            .then(() => {
                allUsers.innerHTML = '';
                outputAllUsers = '';

                fetch(url)
                    .then(res => res.json())
                    .then(users => {
                        return renderMainTable(users)
                    })
            })
        $('#modal-edit').modal('hide');
})

// DELETE - delete user
deleteModal.addEventListener('click', (e) => {
    e.preventDefault();
    console.log(deleteId)
    fetch(`${url}/${deleteId}`, {
        method: 'DELETE'
    })
        .then(() => {
            document.getElementById('row-user-' + deleteId).innerHTML = ""
        })
    $('#modal-delete').modal('hide');
})


// POST - create new user
addForm.addEventListener('submit', (e) => {
    e.preventDefault();
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: $('#nameNewUser').val(),
            surname: $('#surnameNewUser').val(),
            age: $('#ageNewUser').val(),
            email: $('#emailNewUser').val(),
            password: $('#passwordNewUser').val(),
            roles: $('#rolesNewUser').val()
        })
    })
        .then(() => {
            allUsers.innerHTML = '';
            outputAllUsers = '';

            fetch(url)
                .then(res => res.json())
                .then(users => {
                    return renderMainTable(users)
                })
            clearNewFormField()
            document.getElementById('home-tab').click();

        })
})

function clearNewFormField() {
    $('#nameNewUser').val('');
    $('#surnameNewUser').val('');
    $('#ageNewUser').val('');
    $('#emailNewUser').val('');
    $('#passwordNewUser').val('');
    $('#rolesNewUser').val('');
}