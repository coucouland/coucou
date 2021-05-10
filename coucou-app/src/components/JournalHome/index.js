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
import PublishOutlinedIcon from '@material-ui/icons/PublishOutlined';
import BackupOutlinedIcon from '@material-ui/icons/BackupOutlined';
import CreateOutlinedIcon from '@material-ui/icons/CreateOutlined';

const useStyles = makeStyles(theme => ({
    drawer: {
        flexShrink: 0,
    },
    drawerPaper: {
    },
    toolbar: theme.mixins.toolbar,
}));

export default function Journal() {
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
                    <ListItemIcon><CreateOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Drafts" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><PublishOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Published" />
                </ListItem>
                <ListItem button>
                    <ListItemText inset primary="References" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><BackupOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Uploads" />
                </ListItem>
            </List>
            <Divider />
            <List
                subheader={
                    <ListSubheader>Label</ListSubheader>
                }>
                <ListItem button>
                    <ListItemText primary="Label 1" />
                </ListItem>
            </List>
        </Drawer>

        <Container maxWidth="sm">
          <Box my={4}>
            <Typography variant="h4" component="h1" gutterBottom>
              Coucou - Journal
            </Typography>
          </Box>
        </Container>
    </div>
  );
}
