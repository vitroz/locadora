package locadora.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import locadora.dao.HibernateDao;
import locadora.model.Cliente;

@ManagedBean(name = "ClienteMB")
@ViewScoped
public class ClienteMB {

	private Cliente cliente = new Cliente();
	private Cliente clienteSelecionado;
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	private DataModel<?> lista;
	private HibernateDao dao = HibernateDao.getInstance();

	public void salvar(ActionEvent actionEvent) {

		try{
			dao.persist(cliente);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente salvo com sucesso!", null);  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent actionEvent) {

		try{
			dao.merge(clienteSelecionado);
			getCarregaClientes();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente atualizado com sucesso!", null);  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataModel<?> getCarregaClientes() {
		try{
			listaClientes();
		}catch (Exception e) {
			e.printStackTrace();
		}
		lista = new ListDataModel<Cliente>(listaClientes);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> listaClientes() {
		try{
			listaClientes = (List<Cliente>) dao.findAll(Cliente.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listaClientes;
	}
	
	public void excluirCliente(ActionEvent actionEvent){
        dao.remove(Cliente.class, clienteSelecionado.getCodigo());
        getCarregaClientes();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente excluído com sucesso!", null);  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente Cliente) {
		this.cliente = Cliente;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente ClienteSelecionado) {
		this.clienteSelecionado = ClienteSelecionado;
	}

}

