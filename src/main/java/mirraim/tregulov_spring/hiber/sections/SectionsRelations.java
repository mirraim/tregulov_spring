package mirraim.tregulov_spring.hiber.sections;

import mirraim.tregulov_spring.hiber.sections.entity.Child;
import mirraim.tregulov_spring.hiber.sections.entity.Section;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SectionsRelations {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Child.class)
                .addAnnotatedClass(Section.class)
                .buildSessionFactory()) {
            Section section1 = new Section("Math");
            Child child1 = new Child("Hannah", 12);
            Child child2 = new Child("Jane", 13);
            Child child3 = new Child("Kurt", 11);
            section1.addChild(child1);
            section1.addChild(child2);
            section1.addChild(child3);

            Section section4 = new Section("Dance");
            Section section2 = new Section("VolleyBall");
            Child child4 = new Child("Arthur", 12);
            child4.addSection(section2);
            child4.addSection(section4);

            addSection( factory, section1);
            addChild(factory, child4);
            getSection(factory, section1.getId());
            getChild(factory, child4.getId());
            deleteSection(factory, section2.getId());
            deleteChild(factory, section2.getId());
        }
    }

    public static void addSection(SessionFactory factory, Section section) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(section);
            session.getTransaction().commit();
        }
    }

    public static void addChild(SessionFactory factory, Child child) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.persist(child);
            session.getTransaction().commit();
        }
    }

    public static Section getSection(SessionFactory factory, int id) {
        Section section;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            section = session.get(Section.class, id);
            System.out.println(section);
            System.out.println(section.getChildren());
            session.getTransaction().commit();
        }
        return section;
    }

    public static Child getChild(SessionFactory factory, int id) {
        Child child;
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            child = session.get(Child.class, id);
            System.out.println(child);
            System.out.println(child.getSections());
            session.getTransaction().commit();
        }
        return child;
    }

    public  static void deleteChild(SessionFactory factory, int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Child child = session.get(Child.class, id);
            session.remove(child);
            session.getTransaction().commit();
        }
    }

    public static void deleteSection(SessionFactory factory, int id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Section section = session.get(Section.class, id);
            session.remove(section);
            session.getTransaction().commit();
        }
    }
}
