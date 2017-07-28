package org.swf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectActionBean implements Serializable
{

    private static final Logger logger = LoggerFactory.getLogger(ProjectActionBean.class);

    public void viewUsers()
    {
        logger.info("trying to open user dialog");
        Map<String, Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        RequestContext.getCurrentInstance().openDialog("users", options, null);
        logger.info("nothing happened");
    }

    public void onUserSelected(SelectEvent event)
    {
        User user = (User) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, user.getName(), user.getName());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void selectUserFromDialog(User user)
    {
        RequestContext.getCurrentInstance().closeDialog(user);
    }

    public List<User> getUsers()
    {
        return Arrays.asList(new User(100L, "SWF"), new User(200L, "JBoss Seam"));
    }

    public static class User implements Serializable
    {
        private Long id;
        private String name;

        public User()
        {
        }

        public User(Long id, String name)
        {
            this.id = id;
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public Long getId()
        {
            return id;
        }

        public void setId(Long id)
        {
            this.id = id;
        }

        @Override
        public int hashCode()
        {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            User other = (User) obj;
            if (id == null)
            {
                if (other.id != null)
                    return false;
            }
            else if (!id.equals(other.id))
                return false;
            return true;
        }

    }

}
