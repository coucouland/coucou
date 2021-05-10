import React, {useState}  from 'react';
import { Container, Typography, Box, Link, TextField } from '@material-ui/core';
import { AppBar, Toolbar, IconButton, Button } from '@material-ui/core';
import { Menu, MenuItem, Drawer, List, ListItem, ListItemIcon, ListItemText, Divider, ListSubheader } from '@material-ui/core';
import { makeStyles } from '@material-ui/core/styles';
import MenuIcon from '@material-ui/icons/Menu';
import HomeOutlinedIcon from '@material-ui/icons/HomeOutlined';
import ExitToAppOutlinedIcon from '@material-ui/icons/ExitToAppOutlined';
import SettingsOutlinedIcon from '@material-ui/icons/SettingsOutlined';
import StarBorderOutlinedIcon from '@material-ui/icons/StarBorderOutlined';
import TrendingUpOutlinedIcon from '@material-ui/icons/TrendingUpOutlined';
import WatchLaterOutlinedIcon from '@material-ui/icons/WatchLaterOutlined';

import Village from '../Village';
import villages from './villages';

const useStyles = makeStyles(theme => ({
    drawer: {
        flexShrink: 0,
    },
    drawerPaper: {
    },
    toolbar: theme.mixins.toolbar,
}));

export default function Villages() {
  const classes = useStyles();

  const [anchorEl, setAnchorEl] = React.useState(null);
  
  const handleClick = event => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  const [villageCards, setVillageCards] = useState(0);

  const state = {
    searchFilter: '',
  }

  const handleSearch = event => {

      setVillageCards(villages.opml.body.outline[0].outline
        .filter(item => item._title.toLowerCase().indexOf(event.target.value.toLowerCase()) !== -1)
        .map((item) =>
            <Village key={item._title} title={item._title} />));
  };

  return (
    <div>
        <Drawer variant="permanent" anchor="right" className={classes.drawer} classes={{ paper: classes.drawerPaper, }}>
            <div className={classes.toolbar} />
            <List>
                <ListItem button>
                    <ListItemIcon><WatchLaterOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Recent" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><StarBorderOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Starred" />
                </ListItem>
                <ListItem button>
                    <ListItemIcon><TrendingUpOutlinedIcon /></ListItemIcon>
                    <ListItemText primary="Popular" />
                </ListItem>
            </List>
            <Divider />
            <List
                subheader={
                    <ListSubheader>Category</ListSubheader>
                }>
                <ListItem button>
                    <ListItemText primary="News" />
                </ListItem>
                <ListItem button>
                    <ListItemText primary="Technology" />
                </ListItem>
                <ListItem button>
                    <ListItemText primary="Environment" />
                </ListItem>
            </List>
        </Drawer>

        <Container maxWidth="sm">
          <Box my={4}>
            <Typography variant="h4" component="h1" gutterBottom>
              Coucou - Villages
            </Typography>
            <TextField placeholder="Search" onChange={handleSearch}></TextField>
          </Box>
        </Container>

            <Box display="flex" flexWrap="wrap">
          {villageCards}
              </Box>

    </div>
  );
}
