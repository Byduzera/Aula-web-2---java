package br.com.marcos.lojaonline;

public class LojaOnline 
{
	public static void main(String[] args) 
	{
		Cliente cliente = new Cliente("fulano", "fulano@email.com", "123456789");
		
		
		Produto livro = new Produto("O senhor dos Aneis:", 50.0, 0.5);
		Produto jogo = new Produto("Cod bops 6:", 350.0, 0.25);
		Produto console = new Produto("Nintendo Switch", 1000.0, 1.5);
		
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarProduto(livro);
		pedido.adicionarProduto(jogo);
		pedido.adicionarProduto(console);
		pedido.finalizar();
	}

}
