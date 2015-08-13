package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void inicioIgualFimPeriodoDiaria(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
		Sessao sessao = sessoes.get(0);
		verificaDadosSessao(espetaculo, inicio, horario, sessao);
	}

	private void verificaDadosSessao(Espetaculo espetaculo, LocalDate dataSessao,
			LocalTime horario, Sessao sessao) {
		Assert.assertEquals(dataSessao, sessao.getInicio());
		Assert.assertEquals(horario, sessao.getHora());
		Assert.assertEquals(espetaculo, sessao.getEspetaculo());
	}
	
	@Test
	public void fimComUmDiaAMaisDiaria(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(1);
		LocalTime horario = new LocalTime();		
		Periodicidade periodicidade = Periodicidade.DIARIA;
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(2, sessoes.size());
		for (Sessao sessao : sessoes) {
			verificaDadosSessao(espetaculo, inicio, horario, sessao);
		}
	}
	
	@Test
	public void inicioIgualFimPeriodoSemanal(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = new LocalDate();
		LocalTime horario = new LocalTime();
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
	}
	@Test
	public void fimComUmDiaAMaisSemanal(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(1);
		LocalTime horario = new LocalTime();		
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(1, sessoes.size());
	}
	@Test
	public void fimComSeteDiasAMaisSemanal(){
		Espetaculo espetaculo = new Espetaculo();
		LocalDate inicio = new LocalDate();
		LocalDate fim = inicio.plusDays(7);
		LocalTime horario = new LocalTime();		
		Periodicidade periodicidade = Periodicidade.SEMANAL;
		List<Sessao> sessoes = espetaculo.criaSessoes(inicio, fim, horario, periodicidade);
		
		Assert.assertEquals(2, sessoes.size());
	}
//	@Test (expected=NullPointerException.class)
//	public void dadosNulos(){
//		Espetaculo espetaculo = new Espetaculo();
//		LocalDate inicio = null;
//		LocalDate fim = null;
//		LocalTime horario = null;		
//		espetaculo.criaSessoes(inicio, fim, horario, Periodicidade.DIARIA);
//	}
	
}
