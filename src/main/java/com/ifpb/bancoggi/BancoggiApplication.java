package com.ifpb.bancoggi;

import com.ifpb.bancoggi.entidades.Conta;
import com.ifpb.bancoggi.repository.RepositoryConta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoggiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoggiApplication.class, args);
	}

		private RepositoryConta repositoryGeovana;
		private RepositoryConta repositoryIgor;
		private RepositoryConta repositoryGislany;

		public void instanciaContas(){
			repositoryGeovana.registraConta("geovana123", 100);
			repositoryIgor.registraConta("igor123", 101);
			repositoryGislany.registraConta("gislany123", 102);
		}

}
