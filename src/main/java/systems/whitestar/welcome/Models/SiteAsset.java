package systems.whitestar.welcome.Models;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Tom Paulus
 * Created on 12/22/17.
 */
@Entity
@Table(name = "site_assets")
@Data
public class SiteAsset {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column
    String location;
}
