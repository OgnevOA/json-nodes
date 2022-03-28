package telran.b7a;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"uid"})
@ToString
public class Element {
	int category;
	int grade;
	String language;
	String pre_requisite;
	String thumbnail;
	String title;
	String uid;
	String url;
	String vector;
	int xp;
}
