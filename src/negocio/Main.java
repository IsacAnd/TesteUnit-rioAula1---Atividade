package negocio;

public class Main {

	public static void main(String[] args) {

		Filme f1 = new Filme("Java muito feliz", Genero.ROMANCE);
		Filme f2 = new Filme("Java um pouco feliz", Genero.ROMANCE);
		Filme f3 = new Filme("Java um pouco feliz", Genero.COMEDIA);
		Filme f4 = new Filme("Java um pouco feliz", Genero.DRAMA);
		Locacao locacao = new Locacao();
		Locacao locacao2 = new Locacao();
		Locacao locacao3 = new Locacao();
		Locacao locacao4 = new Locacao();
		Transacao transacao = new Transacao();
		locacao.alugar(new Cliente("isac", 2), f1);
		locacao2.alugar(new Cliente("isac", 2), f2);
		locacao3.alugar(new Cliente("isac", 2), f3);
		transacao.alugueis.add(locacao3);
		transacao.alugueis.add(locacao2);
		transacao.alugueis.add(locacao);
		System.out.print(transacao.consultaGenero());
	}

}
