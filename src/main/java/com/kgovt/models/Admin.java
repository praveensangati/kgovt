package com.kgovt.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper=false)
@Table(name="ADMIN")
public class Admin implements Serializable,Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "admin_id")
	private Long adminId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="region")
	private String region;

}
