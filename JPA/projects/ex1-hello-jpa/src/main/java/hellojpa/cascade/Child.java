package hellojpa.cascade;

import jakarta.persistence.*;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "child_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    public Child() {
    }

    public void setParent(final Parent parent) {
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public Parent getParent() {
        return parent;
    }
}
