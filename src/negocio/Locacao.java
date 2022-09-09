package negocio;

public class Locacao {

	protected Cliente cliente;
	protected Filme filme;
	protected double valorAluguel;
	protected String dataEhora;
	
	public void alugar(Cliente c, Filme f) {
		Transacao transacao = new Transacao();
		this.cliente = c;
		this.filme = f;
		transacao.dataEHora(f);
	}
	
	public void setValorAluguel(int valorAluguel) {
		this.valorAluguel = valorAluguel;
	}
}