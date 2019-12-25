$(function () {

    'use strict';

    let porpertyCmpnt = {
        template: `<div class="row" name="property"><div class="col-md-6"><div class="form-group">
                    <label>Name</label><input type="text" class="form-control" name="name" required>
                    </div></div><div class="col-md-6"><div class="form-group">
                    <label>Value</label><input type="text" class="form-control" name="value" required>
                    </div></div></div>`
    };

    let attachmentCmpnt = {
        template: `<div class="row" name="attachment"><div class="col-md-12"><div class="form-group">
                    <label>Attachments (Local absolute path)</label>
                    <input type="text" class="form-control" placeholder="ex: /home/user/Pictures/sanjiheart.png" name="abs-path" required>
                    </div></div></div>`
    };

    new Vue({
        el: 'main',
        components: {
            'property-cmpnt': porpertyCmpnt,
            'attachment-cmpnt': attachmentCmpnt
        },
        data: {
            numOfProperties: 0,
            numOfAttachments: 0
        },
        methods: {
            saveConf(confType, confData) {
                $.ajax({
                    url: `http://localhost:8787/confs/${confType}`,
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify(confData)
                }).done(confResp => {
                    alert(`Your ${confType} configuration has been saved.`);
                }).fail(jqXHR => {
                    console.error(JSON.stringify(jqXHR));
                });
            },
            setPropertyCmpnts() {
                let numOfProperties = parseInt($('#numOfProperties').val());
                if (numOfProperties >= 0) {
                    this.numOfProperties = parseInt($('#numOfProperties').val());
                    console.log(this.numOfProperties);
                }
            },
            setAttachmentCmpnts() {
                let numOfAttachments = parseInt($('#numOfAttachments').val());
                if (numOfAttachments >= 0) {
                    this.numOfAttachments = parseInt($('#numOfAttachments').val());
                    console.log(this.numOfAttachments);
                }
            },
            saveMailServerConf() {
                let properties = {};
                $('div[name="property"]').each((i, e) => {
                    let name = $(e).find('input[name="name"]').val();
                    let value = $(e).find('input[name="value"]').val();
                    if (name !== '' && value !== '') {
                        properties[name] = value;
                    }
                });
                let mailServerConf = {
                    host: $('#host').val(),
                    port: $('#port').val(),
                    username: $('#username').val(),
                    password: $('#password').val(),
                    properties: properties
                };
                console.log(JSON.stringify(mailServerConf));
                this.saveConf('mailServer', mailServerConf);
            },
            saveMailClientConf() {
                let attachments = [];
                $('div[name="attachment"]').each((i, e) => {
                    let absPath = $(e).find('input[name="abs-path"]').val();
                    if (absPath !== '') {
                        attachments.push(absPath);
                    }
                });
                let mailClientConf = {
                    to: $('#to').val().split(';').map(addr => addr.trim()),
                    subject: $('#subject').val(),
                    text: $('#text').val(),
                    withAttachments: this.numOfAttachments === 0 ? false : true,
                    attachments: attachments
                };
                console.log(JSON.stringify(mailClientConf));
                this.saveConf('mailClient', mailClientConf);
            }
        }
    });

});