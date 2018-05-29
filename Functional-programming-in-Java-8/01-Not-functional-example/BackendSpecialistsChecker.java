public class BackendSpecialistsChecker implements SkillsChecker {
	public boolean check(Employee e) {
		return e.isBackendSpecialist();
	}
}