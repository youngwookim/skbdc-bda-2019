Prerequisites
- VirtualBox, https://www.virtualbox.org
- Vagrant, https://www.vagrantup.com/

```
$ cd /path/to/vm
$ ls -als
$ vagrant up
$ vagrant ssh

```

```
vagrant@vagrant-VirtualBox:~$ sudo apt-get update && sudo apt install openjdk-8-jdk git docker docker-compose && sudo service docker restart
vagrant@vagrant-VirtualBox:~$ ls -als /labs
vagrant@vagrant-VirtualBox:~$ cd /path/to/labs/docker
vagrant@vagrant-VirtualBox:~$ sudo docker-compose up

```
