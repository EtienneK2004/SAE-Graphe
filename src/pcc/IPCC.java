package pcc;

import java.util.ArrayList;

public interface IPCC {
	int distance(String entrant, String sortant);
	int chemin(String entrant, String sortant, ArrayList<String> path);
}
