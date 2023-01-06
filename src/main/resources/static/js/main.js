let credorIdInput = document.querySelector('#credor-id');
credorIdInput.addEventListener('blur', () => preencherNome(credorIdInput.value));
let credorNomeInput = document.querySelector('#credor-nome');

function preencherNome(id) {
    fetch(`http://localhost:8080/api/pessoas/${id}`)
        .then(res => res.json())
        .then(res => {
            if(res.nome !== undefined) {
                credorNomeInput.value = res.nome;
            } else {
                credorNomeInput.value = '';
            }
        });
}