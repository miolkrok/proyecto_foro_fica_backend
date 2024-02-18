package com.forofica.uce.repository.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "noticia")
public class Noticia {
	@Id
	@Column(name = "noti_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_noticia")
	@SequenceGenerator(name = "seq_noticia", sequenceName = "seq_noticia", allocationSize = 1)
	private Integer id;

	@Column(name = "noti_titulo")
	private String titulo;

	@Column(name = "noti_texto", columnDefinition = "text")
	private String texto;

	@Column(name = "noti_imagen", columnDefinition = "text")
	private String imagen;

	@Column(name = "noti_video")
	private String video;

	@Column(name = "noti_fecha_publicacion")
	private LocalDateTime fechaPublicacion;

	// SET y GET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public LocalDateTime getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

}
