package solvd.laba.itcompany.patterns;

public class FactoryGenerator {
    public static AbstractFactory getFactory(String choice){
        if (choice.equalsIgnoreCase("Certification")){
            return new CertificationRepositoryFactory();
        } else if(choice.equalsIgnoreCase("Skill")){
            return new SkillRepositoryFactory();
        }
        return null;
    }
}
