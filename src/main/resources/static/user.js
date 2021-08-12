const singleUser = document.querySelector('.tbodyRoleUser')
let outputSingleUser = '';

const url = 'http://localhost:8080/api/users';

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