package hellojpa.cascade;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parent_id")
    private Long id;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Child> children = new ArrayList<>();

    public Parent() {
    }

    public void addChild(final Child child) {
        children.add(child);
        child.setParent(this);
    }

    public Long getId() {
        return id;
    }

    public List<Child> getChildren() {
        return children;
    }
}
