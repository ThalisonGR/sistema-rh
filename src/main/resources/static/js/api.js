const API_BASE_URL = '/rh';

// Função genérica para fazer requisições
async function apiRequest(endpoint, method = 'GET', data = null) {
    const options = {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        }
    };

    if (data) {
        options.body = JSON.stringify(data);
    }

    try {
        const response = await fetch(`${API_BASE_URL}${endpoint}`, options);
        
        if (!response.ok) {
            const errorText = await response.text();
            let errorMessage = '';
        
            if (errorText) {
                try {
                    const errorData = JSON.parse(errorText);
        
                    // prioridade: erro de validação
                    if (errorData.errors) {
                        const firstError = Object.values(errorData.errors)[0];
                        if (firstError) {
                            errorMessage = firstError;
                        }
                    }
        
                    // mensagem padrão
                    if (!errorMessage && errorData.message) {
                        errorMessage = errorData.message;
                    }
        
                    if (!errorMessage && errorData.error) {
                        errorMessage = errorData.error;
                    }
        
                    if (!errorMessage) {
                        errorMessage = errorText;
                    }
        
                } catch (e) {
                    errorMessage = errorText;
                }
            } else {
                errorMessage = `Erro ${response.status}: ${response.statusText}`;
            }
        
            throw new Error(errorMessage);
        }

        // Se a resposta for 204 No Content, retorna null imediatamente
        if (response.status === 204) {
            return null;
        }

        // Verifica o content-type
        const contentType = response.headers.get('content-type');
        
        // Se não há content-type ou não é JSON, verifica se há conteúdo
        if (!contentType || !contentType.includes('application/json')) {
            const text = await response.text();
            // Se estiver vazio, retorna null
            if (!text || text.trim() === '') {
                return null;
            }
            // Se houver conteúdo mas não for JSON, tenta fazer parse mesmo assim
            try {
                return JSON.parse(text);
            } catch (e) {
                return null;
            }
        }

        // Tenta ler e fazer parse do JSON
        const text = await response.text();
        
        // Se o texto estiver vazio, retorna null
        if (!text || text.trim() === '') {
            return null;
        }
        
        // Faz parse do JSON
        return JSON.parse(text);
    } catch (error) {
        console.error('Erro na requisição:', error);
        throw error;
    }
}

// Funcionários
const FuncionarioAPI = {
    listar: (page = 0, size = 10, sortBy = 'id', nome = null) => {
        let url = `/funcionario/listar?page=${page}&size=${size}&sortBy=${sortBy}`;
        if (nome) {
            url += `&nome=${encodeURIComponent(nome)}`;
        }
        return apiRequest(url);
    },
    
    buscarPorId: (id) => 
        apiRequest(`/funcionario/${id}`),
    
    criar: (data) => 
        apiRequest('/funcionario', 'POST', data),
    
    atualizar: (id, data) => 
        apiRequest(`/funcionario/${id}`, 'PUT', data),
    
    excluir: (id) => 
        apiRequest(`/funcionario/${id}`, 'DELETE')
};

// Cargos
const CargoAPI = {
    listar: (page = 0, size = 10, sortBy = 'id', nome = null) => {
        let url = `/cargos/listarCargos?page=${page}&size=${size}&sortBy=${sortBy}`;
        if (nome) {
            url += `&nome=${encodeURIComponent(nome)}`;
        }
        return apiRequest(url);
    },
    
    listarTodos: () => 
        apiRequest('/cargos/todos'),
    
    buscarPorId: (id) => 
        apiRequest(`/cargos/${id}`),
    
    criar: (data) => 
        apiRequest('/cargos', 'POST', data),
    
    atualizar: (id, data) => 
        apiRequest(`/cargos/${id}`, 'PUT', data),
    
    excluir: (id) => 
        apiRequest(`/cargos/${id}`, 'DELETE')
};

// Departamentos
const DepartamentoAPI = {
    listar: (page = 0, size = 10, sortBy = 'id', nome = null) => {
        let url = `/departamento/listar?page=${page}&size=${size}&sortBy=${sortBy}`;
        if (nome) {
            url += `&nome=${encodeURIComponent(nome)}`;
        }
        return apiRequest(url);
    },
    
    listarTodos: () => 
        apiRequest('/departamento/todos'),
    
    buscarPorId: (id) => 
        apiRequest(`/departamento/${id}`),
    
    criar: (data) => 
        apiRequest('/departamento', 'POST', data),
    
    atualizar: (id, data) => 
        apiRequest(`/departamento/${id}`, 'PUT', data),
    
    excluir: (id) => 
        apiRequest(`/departamento/${id}`, 'DELETE')
};

// Função para exibir mensagens de sucesso/erro
function showAlert(message, type = 'success') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    const container = document.querySelector('.container');
    container.insertBefore(alertDiv, container.firstChild);
    
    // Remove automaticamente após 5 segundos
    setTimeout(() => {
        alertDiv.remove();
    }, 5000);
}

