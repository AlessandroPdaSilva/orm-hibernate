package br.main;

import java.util.List;

import org.junit.Test;

import conexao.HibernateUtil;
import dao.DaoGenerico;
import model.Telefone;
import model.Usuario;

public class Teste {
	
	@Test
	public void testeSalvar() {
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		Usuario u = new Usuario();
		u.setNome("Alessandro");
		u.setEmail("alessandro@gmail.com");
		u.setLogin("alessandro");
		u.setSenha("123");
		u.setIdade(21);
	
		
		usuDao.salvar(u);
		
	}
	
	@Test
	public void testeBusca(){
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		Usuario u = new Usuario();
		u.setId(1L);
		
		u = usuDao.pesquisar(u);
		
		System.out.println(u);
	}
	
	@Test
	public void testeBusca2(){
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		Usuario u = new Usuario();

		u = usuDao.pesquisar(1L,u);
		
		System.out.println(u);
	}
	
	@Test
	public void testeEditar() {
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		Usuario u = new Usuario();
		u.setId(1L);
		u.setNome("joao");
		u.setEmail("joao@gmail.com");
		 
		
		usuDao.editar(u);
		
	}
	
	@Test
	public void testeDelete() {
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		Usuario u = usuDao.pesquisar(1L,new Usuario());
		
		usuDao.deletar(u);
		
	}
	
	@Test
	public void testeListaConsulta() {
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		List<Usuario> listaUsu = usuDao.listar(Usuario.class);
		
		for(Usuario u:listaUsu) {
			System.out.println(u);
			System.out.println("----------");
		}
		
	}
	
	@Test
	public void testeQueryPersonalizada() {
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		List<Usuario> listaUsu = usuDao.getEntityManager().createQuery(
				" FROM Usuario WHERE nome = 'alessandro'").getResultList();
		
		for(Usuario u:listaUsu) {
			System.out.println(u);
			System.out.println("----------");
		}
		
	}
	
	@Test
	public void testeQueryPersonalizada2() {
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		List<Usuario> listaUsu = usuDao.getEntityManager().createQuery(
				" FROM Usuario ORDER BY nome")
				.setMaxResults(10)
				.getResultList();
		
		for(Usuario u:listaUsu) {
			System.out.println(u);
			System.out.println("----------");
		}
		
	}
	
	@Test
	public void testeQueryPersonalizada3() {
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		List<Usuario> listaUsu = usuDao.getEntityManager().createQuery(
				" FROM Usuario WHERE nome = :nome OR idade = :idade")
				.setParameter("nome", "alessandro")
				.setParameter("idade", 10)
				.setMaxResults(10)
				.getResultList();
		
		for(Usuario u:listaUsu) {
			System.out.println(u);
			System.out.println("----------");
		}
		
	}
	
	
	@Test
	public void testeNamedQuery() {
		
		DaoGenerico<Usuario> usuDao = new DaoGenerico<Usuario>();
		
		List<Usuario> listaUsu = usuDao.getEntityManager().createNamedQuery("Usuario.buscaPorNome")
				.setParameter("nome", "alessandro")
				.getResultList();
		
		for(Usuario u:listaUsu) {
			System.out.println(u);
			System.out.println("----------");
		}
		
	}
	
	
	@Test
	public void testeGravaTelefone() {
		
		DaoGenerico dao = new DaoGenerico();
		
		Usuario u = new Usuario();
		u = (Usuario) dao.pesquisar(5L,u);
		
		Telefone tel = new Telefone();
		tel.setNumero("1235496");
		tel.setUsuario(u);
		
		dao.salvar(tel); 
		
	}
	
	
	@Test
	public void testeConsultaTelefone() {
		
		DaoGenerico dao = new DaoGenerico();
		
		Usuario u = new Usuario();
		u = (Usuario) dao.pesquisar(5L, u);
		
		for(Telefone tel : u.getTelefones()) {
			System.out.println(tel.getNumero());
		}
		
	}
	
}
