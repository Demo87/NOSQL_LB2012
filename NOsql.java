import java.io.IOException;


public class NOsql {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String nazwa="";
		int jk=0;
		for(int i=0;i<args.length;i++){
			if(args[i].contains("db=")){
				nazwa=args[i].replaceAll("db=", "");
				
			}
		}
		for(int i=0;i<args.length;i++){
			if(args[i].contains("--odczytcouch")){
				KouchDB k = new KouchDB();
				k.czytaj(nazwa);
				jk=1;
				break;
			}
			if(args[i].contains("--zapisdomongo")){
				MojeMongo m = new MojeMongo();
				m.wstaw(nazwa);
				jk=1;
				break;
				
			}
			if(args[i].contains("--MR")){
				MojeMongo n = new MojeMongo();
				n.wstaw(nazwa);
				n.wstawMR(nazwa);
				jk=1;
				break;
			}
			
		}
		if(jk==0){
		System.out.println("Złe parametry !!!");
		}

	}

}
