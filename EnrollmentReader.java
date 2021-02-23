import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

// write a program that will read the content of the file and separate enrollees
// by insurance company in its own file. Additionally, sort the contents of each file 
// by last and first name (ascending).  Lastly, if there are duplicate User Ids for 
// the same Insurance Company, then only the record with the highest version should 
// be included. The following data points are included in the file:
// User Id (string)
// First Name (string) 
// Last Name (string)
// Version (integer)
// Insurance Company (string)

public class EnrollmentReader {

	// Delimiters which has to be in the CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String LINE_SEPARATOR = "\n";

	public static void main(String[] args) throws IOException, CsvValidationException {

		var fileName = "./resources/testFile.csv";

		try (var fr = new FileReader(fileName, StandardCharsets.UTF_8); var reader = new CSVReader(fr)) {

			String[] nextLine;

			while ((nextLine = reader.readNext()) != null) {

				for (var e : nextLine) {

				}
			}
		}

		List<User> userList = new ArrayList<>();
		String fileIn = "./resources/testFile.csv";
		String fileOut = "./resources/orderedTestFile.csv";
		String line = null;

		// Read all lines in from CSV file and add to studentList
		FileReader fileReader = new FileReader(fileIn);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		while ((line = bufferedReader.readLine()) != null) {
			String[] temp = line.split(",");
			String userId = temp[0].trim();
			String firstName = temp[1].trim();
			String lastName = temp[2].trim();
			int version = Integer.parseInt(temp[3].trim());
			String company = temp[4].trim();

			userList.add(new User(userId, firstName, lastName, version, company));

		}
		bufferedReader.close();

		// TODO remove if there are duplicate User Ids for the same Insurance Company,
		// then only the record with the highest version should be included
		List<Integer> myList = new ArrayList<Integer>();

		for (int i = 0; i < userList.size(); i++) {

			for (int j = 0; j < userList.size(); j++) {
				if (userList.get(i).getCompanyUser().equals(userList.get(j).getCompanyUser())
						&& userList.get(i).getVersion() > userList.get(j).getVersion()) {
					myList.add(j);
				}
			}
		}

		for (int k = myList.size(); k-- > 0;) {
			int num = myList.get(k);
			userList.remove(num);
		}


		// sorts user by last name and first name ascending
		Collections.sort(userList, new FirstNameSorter());
		Collections.sort(userList, new LastNameSorter());

		List<List<User>> separated = separateCompanies(userList);

		// TODO separate enrollees by insurance company in its own file
		Set<String> companyNames = new HashSet<>();
		for (int i = 0; i < userList.size(); i++) {
			companyNames.add(userList.get(i).getCompany());
		}
		
		for (String company : companyNames) {
			String key = company.toString();
			File file = File.createTempFile(key, ".csv", new File("./resources/"));

			Writer writer = new OutputStreamWriter(new FileOutputStream(file));		
			writer.close();
		}

		String[] entries = { "book1", "coin", "pencil", "cup" };
        String fileName2 = "./resources/items3.csv";
        
        
        print(separated);
        
        List<String[]> entries2 = new ArrayList<>();
//        String[] items1 = {"book", "coin", "pencil"};
//        String[] items2 = {"book", "coin", "pencil"};
//        entries2.add(items1);
//        entries2.add(items2);
        
        String fileName3 = "./resources/items4.csv";

        try (var fos = new FileOutputStream(fileName3);
             var osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             var writer = new CSVWriter(osw)) {

            writer.writeAll(entries2);
        }
        
        String[] array = new String[companyNames.size()];
        int index = 0;
        for (Object value : separated) {
          array[index] = (String) value.toString();
          index++;
        }
        entries2.add(array);

	}
	
	
	public static void print(List<List<User>> separated){
		for (List<User> company : separated){
		    for(User user : company){
		        System.out.println(user);
		        String temp = user.toString();
		        
		    }
		}
	}

	// separates users based on company name
	public static List<List<User>> separateCompanies(List<User> userList) {
		final List<List<User>> groupedUsers = userList.stream().collect(Collectors.groupingBy(o -> o.getCompany()))
				.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());

		return groupedUsers;
	}

}

class FirstNameSorter implements Comparator<User> {
	public int compare(User o1, User o2) {
		return o1.getFirstName().compareTo(o2.getFirstName());
	}
}

class LastNameSorter implements Comparator<User> {
	public int compare(User o1, User o2) {
		return o1.getLastName().compareTo(o2.getLastName());
	}
}
