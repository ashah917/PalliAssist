console.log("messaging.js");

$(function() {
    console.log('channels: ' + channels);
    // Get handle to the chat div 
    var $chatWindow = $('#chat-messages');
    var $savedWindow = $('#saved-messages');

    console.log('chatWindow: ' + $chatWindow);

    // Manages the state of our access token we got from the server
    var accessManager;

    // Our interface to the IP Messaging service
    var messagingClient;

    // A handle to the chat channel - the one and only channel we
    // will have in this sample app
    var channel;

    // The server will assign the client a random username - store that value
    // here
    // django_username is saved from html
    console.log('django_username: ' + django_username);

    function clearNewChatModal() {
        $('#new-chat-name').val('');
        $('#new-chat-username').val('');
    }
    $('#close-chat-modal-btn').click(clearNewChatModal);
    $('#x-chat-modal-btn').click(clearNewChatModal);

    function clearAddMemberModal() {
        $('#add-username').val('');
    }
    $('#close-add-modal-btn').click(clearAddMemberModal);
    $('#x-add-modal-btn').click(clearAddMemberModal);

     // Clear it initially upon page load.
    clearNewChatModal();
    clearAddMemberModal();

    // Helper function to print info messages to the chat window
    function print(infoMessage, asHtml) {
        var $msg = $('<div class="info">');
        if (asHtml) {
            $msg.html(infoMessage);
        } else {
            $msg.text(infoMessage);
        }
        $chatWindow.append($msg);
    }

    // Helper function to print chat message to the chat window
    function printMessage(fromUser, timestamp, message) {
        console.log(timestamp.toDateString());
        console.log(timestamp.toString());
        console.log(timestamp.toUTCString());
        console.log(timestamp.toLocaleString());
        console.log(timestamp.toTimeString());
        var $user = $('<span class="username">').text(fromUser + ' [' + timestamp.toString() + ']: ');
        if (fromUser === django_username) {
            $user.addClass('me');
        }
        var $message = $('<span class="message">').text(message);
        var $container = $('<div class="message-container">');
        $container.append($user).append($message);
        $chatWindow.append($container);
        $chatWindow.scrollTop($chatWindow[0].scrollHeight);
    }

    // Alert the user they have been assigned a random username
    print('Logging in...');

    print('Your django_username: ' 
        + '<span class="me">' + django_username + '</span>', true);

    // Initialize the IP messaging client
    console.log('twilio token: ' + token);
    // var token set in html. from django variable.
    accessManager = new Twilio.AccessManager(token);
    messagingClient = new Twilio.IPMessaging.Client(accessManager);
    console.log('messagingClient: ' + messagingClient);

    // TODO. need to set channelName
    var channelName = channels[0]['unique_name'];
    console.log('channel unique_name: ' + channelName);
    // Get the general chat channel, which is where all the messages are
    // sent in this simple application
    var promise = messagingClient.getChannelByUniqueName(channelName);
    promise.then(function(ch) {
        channel = ch;
        console.log('channel: ' + channel);
        if (!channel) {
            console.log('ERROR channel = null');
        } else {
            setupChannel();
        }
    });

    /*
    // Get an access token for the current user, passing a username (identity)
    // and a device ID - for browser-based apps, we'll always just use the 
    // value "browser"
    $.getJSON('/token', {
        identity: username,
        device: 'browser'
    }, function(data) {
        console.log('data from /token:');
        console.log(data);
        // Alert the user they have been assigned a random username
        //username = data.identity;
        print('Your username: ' 
            + '<span class="me">' + username + '</span>', true);

        // Initialize the IP messaging client
        accessManager = new Twilio.AccessManager(data.token);
        messagingClient = new Twilio.IPMessaging.Client(accessManager);

        channelName = 'Demo Channel';
        channelFriendlyName = 'Demo Channel';

        // Get the general chat channel, which is where all the messages are
        // sent in this simple application
        print('Attempting to join "' + channelFriendlyName + '" chat channel...');
        var promise = messagingClient.getChannelByUniqueName(channelName);
        promise.then(function(ch) {
            console.log('channel before: ' + channel);
            channel = ch;
            console.log('channel after: ');
            console.log(channel);
            if (!channel) {
                console.log('channel = null');
                // If it doesn't exist, let's create it
                messagingClient.createChannel({
                    uniqueName: channelName,
                    friendlyName:channelFriendlyName
                }).then(function(channel) {
                    console.log('Created ' + channelName + ':');
                    console.log(ch);
                    channel = ch;
                    setupChannel();
                }, function(err) {
                    console.log('ran into error');
                    console.log(err);
                    console.log('Found ' + channelName + ' channel:');
                    console.log(channel);
                    setupChannel();
                });
            } else {
                console.log('Found ' + channelName + ' channel:');
                console.log(channel);
                setupChannel();
            }
        })
    });
    */

    function loadPreviousMessages() {
        console.log(channel);
        channel.getMessages().then(function(messages) {
            var totalMessages = messages.length; 

            for (var i = 0; i < messages.length; i++) {
                var message = messages[i];
                console.log("message");
                console.log(message);
                printMessage(message.author, message.timestamp, message.body);
                console.log('Author: ' + message.author);
            }
            console.log('Total Messages: ' + totalMessages);
        });
    }


    // Set up channel after it has been found
    function setupChannel() {
        // Join the general channel
        /*
        channel.join().then(function(channel) { print('Joined channel as ' 
                + '<span class="me">' + django_username + '</span>.', true);
        });
        */
        console.log('in setupchannel');
        console.log(channel.friendlyName);

        // Listen for new messages sent to the channel
        channel.on('messageAdded', function(message) {
            printMessage(message.author, message.timestamp, message.body);

            console.log("===========");
            console.log('received message at timestamp: ' + message.timestamp.getTime());
        });
        loadPreviousMessages();
    }

    // Send a new message to the general channel
    var $chatInput = $('#chat-input');
    $chatInput.on('keydown', function(e) {
        if (e.keyCode == 13) {
            console.log('sent message: ' + $chatInput.val());
            channel.sendMessage($chatInput.val())
            $chatInput.val('');
        }
    });

    // Save a message to db
    var $saveInput = $('#save-input');
    $saveInput.on('keydown', function(e) {
        if (e.keyCode == 13) {
            var $msg = $('<div class="info">');
            $msg.text('Saved: "' + $saveInput.val() + '"');

            //$.post('/saveMessage', {'message': $saveInput.val()});

            $.getJSON('/save-message', {
                content: $saveInput.val(),
                sender: django_username,
                channel: channelName,
                time_sent: $.now(),
                type: 'text'
            }, function(data) {})

            $savedWindow.append($msg);
            $saveInput.val('');
        }
    });

    /*
    // Javascript to enable link to tab
    var url = document.location.toString();
    if (url.match('#')) {
        console.log(url.split('#')[1]);
        $('.list-group a[href="#' + url.split('#')[1] + '"]').tab('show');
    }  else {
        $('.list-group a[href="#' + channels[0]['sid'] + '"]').tab('show');
    }

    // Change hash for page-reload
    $('.list-group a').on('shown.bs.tab', function (e) {
        console.log('e.target.has');
        window.location.hash = e.target.hash;
    })
    */

    $('#new-chat-btn').click(function() {
        console.log('messages.js: new message');
    })

    $('#create-chat-btn').click(function() {
        $('#create-chat-btn').html('Loading...');
        $('#create-chat-btn').prop('disabled', true);

        data = {
            'csrfmiddlewaretoken': Cookies.get('csrftoken'),
            'channel_name': $('#new-chat-name').val(),
            'add_member': $('#new-chat-username').val(),
        }

        $.post('/create-channel', data, function(response) {
            console.log(response);
            location.reload();
        });

    });

    $('#add-member-btn').click(function() {
        $('#add-member-btn').html('Adding...');
        $('#add-member-btn').prop('disabled', true);

        data = {
            'csrfmiddlewaretoken': Cookies.get('csrftoken'),
            'channel_name': $('#new-message-name').val(),
            'new_members': $('#add-username').val(),
        }

        $.post('/add-member', data, function(response) {
            console.log(response);
            location.reload();
        });

    });

    $('.list-group-item').each(function() {
        console.log($(this));
        $(this).click(function() {
            console.log('clicked');
            console.log($(this));
            var sid = $(this).attr('href').slice(1); // Ignore the # in the href
            console.log(sid);
            var promise = messagingClient.getChannelBySid(sid);
            //console.log($(this).addClass('active'));
            promise.then(function(ch) {
                channel = ch;
                console.log('channel: ' + channel);
                if (!channel) {
                    console.log('ERROR channel = null');
                } else {
                    setupChannel();
                }
            });
        })
    })


});
