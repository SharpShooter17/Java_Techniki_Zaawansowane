public class FrontendSpecialistsChecker implements SkillsChecker {
	public boolean check(Employee e) {
		return e.isFrontendSpecialist();
	}
}