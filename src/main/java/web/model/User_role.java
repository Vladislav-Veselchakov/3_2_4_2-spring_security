package web.model;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(
        name = "UserRole",
        classes = @ConstructorResult(
                targetClass = User_role.class,
                columns = {
                        //@ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "user_id"),
                        @ColumnResult(name = "role_id")
                        //        @ColumnResult(name = "numBooks", type = Long.class)
                }))

public class User_role {
    public Long user_id;
    public long role_id;
}


/** example from https://stackoverflow.com/questions/25188939/how-to-map-the-result-set-of-a-jpa-nativequery-to-a-pojo-using-sqlresultsetmappi
@SqlResultSetMapping(name="foo",
        classes = {
                @ConstructorResult(
                        targetClass = Bar.class,
                        columns = {
                                @ColumnResult(name = "barId", type = Long.class),
                                @ColumnResult(name = "barName", type = String.class),
                                @ColumnResult(name = "barTotal", type = Long.class)
                        })
        })

public class Bar {

    private Long barId;
    private String barName;
    private Long barTotal;
*/
