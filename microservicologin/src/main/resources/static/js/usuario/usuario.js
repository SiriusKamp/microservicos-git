class Usuario {
    id = '';
    nome = '';
    sexo = '';
    email = '';
    grupo = '';
    ativo = '';
    senha = '';

    criarUsuario(id, nome, sexo, email, grupo, ativo, senha) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.email = email;
        this.grupo = grupo;
        this.ativo = ativo;
        this.senha = senha;
    }

    salvar() {
        let formData = new FormData(); //para envio dos dados.
        formData.append('id', this.id); //inicializando o parâmetro id.
        formData.append('nome', this.nome); //inicializando o parâmetro nome.
        formData.append('sexo', this.sexo); //inicializando o parâmetro sexo.
        formData.append('email', this.email); //inicializando o parâmetro email.
        formData.append('grupo', this.grupo); //inicializando o parâmetro grupo.
        formData.append('ativo', this.ativo); //inicializando o parâmetro ativo.
        formData.append('senha', this.senha); //inicializando o parâmetro senha.
        listarObjeto('POST', '/usuario', formData, function (result) {
            if (result.status == 200)
                visualizar(JSON.parse(result.responseText));
            else
                visualizarAlert(result.responseText);
        });
    }

    listar() {
        let formData = new FormData(); //para envio dos dados.
        formData.append('nome', this.nome); //inicializando o parâmetro nome.
        formData.append('email', this.email); //inicializando o parâmetro email.
        formData.append('ativo', this.ativo); //inicializando o parâmetro ativo.
        listarObjeto('POST', '/usuario/listar', formData, function (result) {
            if (result.status == 200)
                visualizar(JSON.parse(result.responseText));
            else
                visualizarAlert(result.responseText);
        });
    }

    buscarPorId() {
        listarObjeto('GET', '/usuario/' + this.id, null, function (result) {
            if (result.status == 200)
                visualizar(JSON.parse(result.responseText));
            else
                visualizarAlert(result.responseText);
        });
    }

    alterar() {
        let formData = new FormData(); //para envio dos dados.
        formData.append('id', this.id);
        formData.append('nome', this.nome); //inicializando o parâmetro nome.
        formData.append('sexo', this.sexo); //inicializando o parâmetro sexo.
        formData.append('email', this.email); //inicializando o parâmetro email.
        formData.append('grupo', this.grupo); //inicializando o parâmetro grupo.
        formData.append('ativo', this.ativo); //inicializando o parâmetro ativo.
        listarObjeto('PUT', '/usuario/' + this.id, null, function (result) {
            if (result.status == 200)
                visualizar(JSON.parse(result.responseText));
            else
                visualizarAlert(result.responseText);
        });
    }

    excluir() {
        listarObjeto('DELETE', '/usuario/' + this.id, null, function (result) {
            console.log("Usuário: ", JSON.parse(result.responseText));
            /*if (result.status == 200)
                visualizar(JSON.parse(result.responseText));
            else
                visualizarAlert(result.responseText);*/
        });
    }
}