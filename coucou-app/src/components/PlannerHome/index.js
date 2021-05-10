import React from 'react';
import { Container, Typography, Box, Link } from '@material-ui/core';
import { AppBar, Toolbar, IconButton, Button } from '@material-ui/core';
import { Menu, MenuItem, Drawer, List, ListItem, ListItemIcon, ListItemText, Divider, ListSubheader } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import MenuIcon from '@material-ui/icons/Menu';
import HomeOutlinedIcon from '@material-ui/icons/HomeOutlined';
import ExitToAppOutlinedIcon from '@material-ui/icons/ExitToAppOutlined';
import SettingsOutlinedIcon from '@material-ui/icons/SettingsOutlined';
import WatchLaterOutlinedIcon from '@material-ui/icons/WatchLaterOutlined';

const useStyles = makeStyles(theme => ({
    drawer: {
        flexShrink: 0,
    },
    drawerPaper: {
    },
    toolbar: theme.mixins.toolbar,
}));

export default function Planner() {
  const classes = useStyles();

  const [anchorEl, setAnchorEl] = React.useState(null);
  
  const handleClick = event => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <div>
        <Drawer variant="permanent" anchor="right" className={classes.drawer} classes={{ paper: classes.drawerPaper, }}>
            <div className={classes.toolbar} />
            <List>
                <ListItem button>
                    <ListItemIcon><WatchLaterOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Schedule" />
                </ListItem>
                <ListItem button>
                    <ListItemText inset primary="Today" />
                </ListItem>
                <ListItem button>
                    <ListItemText inset primary="This Week" />
                </ListItem>
            </List>
            <Divider />
            <List
                subheader={
                    <ListSubheader>Type</ListSubheader>
                }>
                <ListItem button>
                    <ListItemText primary="Events" />
                </ListItem>
                <ListItem button>
                    <ListItemText primary="Tasks" />
                </ListItem>
            </List>
        </Drawer>

        <Container maxWidth="sm">
          <Box my={4}>
            <Typography variant="h4" component="h1" gutterBottom>
              Coucou - Planner
            </Typography>
          </Box>
        </Container>
    </div>
  );
}
