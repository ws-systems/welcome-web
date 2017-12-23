package systems.whitestar.welcome.Models;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Entity
@Table(name = "quotes")
@Data
@NoArgsConstructor
public class Quote {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Expose
    @NonNull
    @Column()
    String author;

    @Expose
    @NonNull
    @Column()
    String text;
}
