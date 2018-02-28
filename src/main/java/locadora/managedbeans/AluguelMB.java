package locadora.managedbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import locadora.dao.HibernateDao;
import locadora.model.Aluguel;

@ManagedBean(name = "AluguelMB")
@ViewScoped
public class AluguelMB {

	private Aluguel aluguel = new Aluguel();
	private Aluguel aluguelSelecionado;
	private List<Aluguel> listaAluguel = new ArrayList<Aluguel>();
	private DataModel<?> lista;
	private HibernateDao dao = HibernateDao.getInstance();

	public void salvar(ActionEvent actionEvent) {

		try{
			dao.persist(aluguel);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluguel salvo com sucesso!", null);  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void alterar(ActionEvent actionEvent) {

		try{
			dao.merge(aluguelSelecionado);
			getCarregaAluguel();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluguel atualizado com sucesso!", null);  
	        FacesContext.getCurrentInstance().addMessage(null, msg); 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public DataModel<?> getCarregaAluguel() {
		try{
			listaAluguel = (List<Aluguel>) dao.findAll(Aluguel.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		lista = new ListDataModel<Aluguel>(listaAluguel);
		return lista;
	}
	
	public void excluirAluguel(ActionEvent actionEvent){
        dao.remove(Aluguel.class, aluguelSelecionado.getCodigo());
        getCarregaAluguel();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluguel excluído com sucesso!", null);  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
    }

	public List<Aluguel> getListaAluguel() {
		return listaAluguel;
	}

	public void setListaAluguel(List<Aluguel> listaAluguels) {
		this.listaAluguel = listaAluguels;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}
	public void setAluguel(Aluguel Aluguel) {
		this.aluguel = Aluguel;
	}

	public Aluguel getAluguelSelecionado() {
		return aluguelSelecionado;
	}

	public void setAluguelSelecionado(Aluguel AluguelSelecionado) {
		this.aluguelSelecionado = AluguelSelecionado;
	}
	
	public Date getHoje(){
		Date currentDate = new Date();
		return currentDate;
	}

}

