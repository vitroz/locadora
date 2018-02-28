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
import locadora.model.Carro;

@ManagedBean(name = "CarroMB")
@ViewScoped
public class CarroMB {

	private Carro carro = new Carro();
	private Carro carroSelecionado;
	private List<Carro> listaCarros = new ArrayList<Carro>();
	private DataModel<?> lista;
	private HibernateDao dao = HibernateDao.getInstance();

	public void salvar(ActionEvent actionEvent) {
		try{
			dao.persist(carro);
			carro = new Carro();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Carro salvo com sucesso!", null);  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent actionEvent) {

		try{
			dao.merge(carroSelecionado);
			getCarregaCarros();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Carro atualizado com sucesso!", null);  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DataModel<?> getCarregaCarros() {
		try{
			listaCarros();
		}catch (Exception e) {
			e.printStackTrace();
		}
		lista = new ListDataModel<Carro>(listaCarros);
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Carro> listaCarros() {
		try{
			listaCarros = (List<Carro>) dao.findAll(Carro.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listaCarros;
	}
	
	public void excluirCarro(ActionEvent actionEvent){
        dao.remove(Carro.class, carroSelecionado.getCodigo());
        getCarregaCarros();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Carro excluído com sucesso!", null);  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }

	public List<Carro> getListaCarros() {
		return listaCarros;
	}

	public void setListaCarros(List<Carro> listaCarros) {
		this.listaCarros = listaCarros;
	}

	public Carro getCarro() {
		return carro;
	}
	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

}

