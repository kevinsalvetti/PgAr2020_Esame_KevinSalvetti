package unipoly;

public class Giocatore {

	private final static String INSERT_NOME = "Inserisci il nome del Giocatore \t >> \t";
	private final static Integer INITIAL_SOLDI = 500000;

	private String nome;
	private Integer soldi;
	private boolean imprigionato;
	private boolean ticket;
	private boolean propietarioAlbergo;
	private boolean propietarioCasa;
	private Integer compratoCasa;
	private Integer compratoAlbergo;

	// costruttore
	public Giocatore(String nome, Integer soldi, boolean imprigionato, boolean ticket, boolean propietarioAlbergo,
			boolean propietarioCasa, Integer compratoCasa, Integer compratoAlbergo) {
		super();
		this.nome = nome;
		this.soldi = soldi;
		this.imprigionato = imprigionato;
		this.ticket = ticket;
		this.propietarioAlbergo = propietarioAlbergo;
		this.propietarioCasa = propietarioCasa;
		this.compratoCasa = compratoCasa;
		this.compratoAlbergo = compratoAlbergo;
	}

	public boolean isPropietarioAlbergo() {
		return propietarioAlbergo;
	}

	public void setPropietarioAlbergo(boolean propietarioAlbergo) {
		this.propietarioAlbergo = propietarioAlbergo;
	}

	public boolean isPropietarioCasa() {
		return propietarioCasa;
	}

	public void setPropietarioCasa(boolean propietarioCasa) {
		this.propietarioCasa = propietarioCasa;
	}

	public Integer getCompratoCasa() {
		return compratoCasa;
	}

	public void setCompratoCasa(Integer compratoCasa) {
		this.compratoCasa = compratoCasa;
	}

	public Integer getCompratoAlbergo() {
		return compratoAlbergo;
	}

	public void setCompratoAlbergo(Integer compratoAlbergo) {
		this.compratoAlbergo = compratoAlbergo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getSoldi() {
		return soldi;
	}

	public void setSoldi(int soldi) {
		this.soldi = soldi;
	}
	// inserire nome giocatore

	public boolean isImprigionato() {
		return imprigionato;
	}

	public void setImprigionato(boolean imprigionato) {
		this.imprigionato = imprigionato;
	}

	public boolean isTicket() {
		return ticket;
	}

	public void setTicket(boolean ticket) {
		this.ticket = ticket;
	}

	public void NomeGiocatore() {

		setNome(mylib.InputDati.leggiStringaNonVuota(INSERT_NOME));

	}

	// set soldi iniziali
	public void SoldiGiocatore() {
		setSoldi(INITIAL_SOLDI);
	}

	// controllo vincita
	public boolean Win() {
		if (getSoldi() > 1000000) {
			return true;
		}
		return false;
	}

	// controllo perdita
	public boolean Lose() {
		if (getSoldi() < 0) {
			return true;
		}
		return false;
	}

	public void AggiuntaSoldi(int soldi) {
		setSoldi(getSoldi() + soldi);
	}

	public void TogliSoldi(int soldi) {
		setSoldi(getSoldi() - soldi);
	}

}
