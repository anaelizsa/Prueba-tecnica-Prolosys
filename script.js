const API_URL = 'http://localhost:8080/api/employees'; // Cambia el puerto si tu backend usa otro
const form = document.getElementById('employee-form');
const tableBody = document.getElementById('employee-table-body');
let editingEmployeeId = null; // Para saber si estamos editando o creando

document.addEventListener('DOMContentLoaded', loadEmployees);

form.addEventListener('submit', function(event) {
    event.preventDefault();
    if (editingEmployeeId) {
        updateEmployee(editingEmployeeId);
    } else {
        createEmployee();
    }
});

function loadEmployees() {
    fetch(API_URL)
        .then(response => response.json())
        .then(data => {
            tableBody.innerHTML = '';
            data.forEach(employee => addEmployeeToTable(employee));
        })
        .catch(error => console.error('Error cargando empleados:', error));
}

function createEmployee() {
    const employee = getFormData();

    fetch(`${API_URL}/add-employee`, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(employee)
    })
    .then(response => response.json())
    .then(data => {
        addEmployeeToTable(data);
        form.reset();
    })
    .catch(error => console.error('Error creando empleado:', error));
}

function updateEmployee(id) {
    const employee = getFormData();

    fetch(`${API_URL}/update-employee/${id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(employee)
    })
    .then(response => {
        if (response.ok) {
            loadEmployees();
            form.reset();
            editingEmployeeId = null;
            document.getElementById('submit-btn').innerText = 'Agregar Empleado';
        }
    })
    .catch(error => console.error('Error actualizando empleado:', error));
}

function deleteEmployee(id) {
    fetch(`${API_URL}/delete-employee/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            loadEmployees();
        }
    })
    .catch(error => console.error('Error eliminando empleado:', error));
}

function editEmployee(employee) {
    document.getElementById('name').value = employee.name;
    document.getElementById('paternalSurname').value = employee.paternalSurname;
    document.getElementById('maternalSurname').value = employee.maternalSurname;
    document.getElementById('company').value = employee.company;
    document.getElementById('gender').value = employee.gender;
    document.getElementById('birthdate').value = employee.birthdate;
    document.getElementById('curp').value = employee.curp;
    document.getElementById('rfc').value = employee.rfc;

    editingEmployeeId = employee.employeeNumber;
    document.getElementById('submit-btn').innerText = 'Actualizar Empleado';
}

function getFormData() {
    return {
        name: document.getElementById('name').value,
        paternalSurname: document.getElementById('paternalSurname').value,
        maternalSurname: document.getElementById('maternalSurname').value,
        company: document.getElementById('company').value,
        gender: document.getElementById('gender').value,
        birthdate: document.getElementById('birthdate').value,
        curp: document.getElementById('curp').value,
        rfc: document.getElementById('rfc').value
    };
}

function addEmployeeToTable(employee) {
    const row = document.createElement('tr');

    row.innerHTML = `
        <td>${employee.employeeNumber}</td>
        <td>${employee.name}</td>
        <td>${employee.paternalSurname}</td>
        <td>${employee.maternalSurname}</td>
        <td>${employee.company}</td>
        <td>${employee.gender}</td>
        <td>${employee.birthdate}</td>
        <td>${employee.curp}</td>
        <td>${employee.rfc}</td>
        <td>
            <button onclick='editEmployee(${JSON.stringify(employee)})'>Editar</button>
            <button onclick='deleteEmployee("${employee.employeeNumber}")'>Eliminar</button>
        </td>
    `;

    tableBody.appendChild(row);
}

function searchEmployee() {
    const searchId = document.getElementById('searchId').value;
    if (searchId.trim() === "") return;

    fetch(`${API_URL}/${searchId}`)
        .then(response => {
            if (!response.ok) {
                alert("Empleado no encontrado.");
                return;
            }
            return response.json();
        })
        .then(employee => {
            const tbody = document.getElementById('employee-table-body');
            tbody.innerHTML = '';

            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${employee.employeeNumber}</td>
                <td>${employee.name}</td>
                <td>${employee.paternalSurname}</td>
                <td>${employee.maternalSurname}</td>
                <td>${employee.company}</td>
                <td>${employee.gender}</td>
                <td>${employee.birthdate}</td>
                <td>${employee.curp}</td>
                <td>${employee.rfc}</td>
                <td class="actions">
                    <button onclick="editEmployee('${employee.employeeNumber}')">Editar</button>
                    <button onclick="deleteEmployee('${employee.employeeNumber}')">Eliminar</button>
                </td>
            `;
            tbody.appendChild(row);
        });
}
