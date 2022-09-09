package negocio;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Transacao {

	protected  ArrayList<Locacao> alugueis;
	protected  ArrayList<Filme> favoritos;
	
	public Transacao() {
		
		alugueis= new ArrayList<Locacao>();
		favoritos = new ArrayList<Filme>();
	}
	
	public void filmesFavoritos(Filme f) {
		favoritos.add(f);
	}
	
	public void alugarFilmesFavoritos(Cliente c) {
		Locacao locacao = new Locacao();
		for(Filme filme : favoritos) {
			locacao.alugar(c, filme);
		}
	}
	
	public double descontoRomance(Filme filme) {
		double v = 0;
		for (Locacao locacao : alugueis) {
			if (locacao.filme.genero == Genero.ROMANCE) {
				locacao.valorAluguel = locacao.valorAluguel - (locacao.valorAluguel * 0.1);
				v = locacao.valorAluguel;
			}
		}
		return v;
	}
	
	public double valorLocacaoTotal() {
	    double valor=0;
		for (Locacao locacao : alugueis) {
			valor +=locacao.filme.valorCompra;
		}
		return valor;
	}
	
	public Cliente buscaCliente(int id) {
		for (Locacao locacao : alugueis) {
			if(locacao.cliente.id==id) {
				return locacao.cliente;
			}
			
		}
		return null;
	}
	
	public Genero consultaGenero() {
		int R = 0, C = 0, D = 0;
		for (Locacao locacao : alugueis) {
			if(locacao.filme.genero == Genero.ROMANCE) {
				R++;
				System.out.println("ROMANCE");
			} else if (locacao.filme.genero == Genero.COMEDIA) {
				C++;
				System.out.println("COMEDIA");
			} else {
				D++;
				System.out.println("DRAMA");
			}
		}
		if (R > D && R > C) {
			return Genero.ROMANCE;
		} else if (C > D && R > C) {
			return Genero.COMEDIA;
		} else {
			return Genero.DRAMA;
		}

	}
	
	public String dataEHora (Filme f) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        String g = dtf.format(LocalDateTime.now());
        for (Locacao locacao : alugueis) {
        	if(locacao.filme == f) {
        		locacao.filme.dataEhora = g;
        	}
        }
        return g;
	}
	
	public void retornaDataEHora(Filme f) {
		for (Locacao locacao : alugueis) {
				String g = locacao.filme.dataEhora;
				System.out.print(g);
		}
	}
	public double calculoLucro(int filmeId) {
		double valor=0;
		Filme aux = null;
		for (Locacao locacao : alugueis) {
			if(locacao.filme.id==filmeId) {
				valor += locacao.valorAluguel;
				aux = locacao.filme;
			}
			
		}
		return (valor*100)/aux.valorCompra;
	}
	
}