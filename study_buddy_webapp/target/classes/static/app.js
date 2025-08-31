const api = (p) => `/api${p}`;


async function createStudent(){
const name = document.getElementById('name').value.trim();
const email = document.getElementById('email').value.trim();
const courses = document.getElementById('courses').value.split(',').map(s=>s.trim()).filter(Boolean);
const res = await fetch(api('/students'),{method:'POST',headers:{'Content-Type':'application/json'},body:JSON.stringify({name,email,courses})});
document.getElementById('createOut').textContent = JSON.stringify(await res.json(), null, 2);
}


async function addAvailability(){
const id = document.getElementById('av-student').value;
const day = document.getElementById('av-day').value;
const start = document.getElementById('av-start').value;
const end = document.getElementById('av-end').value;
const res = await fetch(api(`/students/${id}/availability?day=${day}&start=${start}&end=${end}`),{method:'POST'});
document.getElementById('avOut').textContent = JSON.stringify(await res.json(), null, 2);
}


async function searchCourse(){
const course = document.getElementById('course').value.trim();
const res = await fetch(api(`/students?course=${encodeURIComponent(course)}`));
const students = await res.json();
let html = `<table><tr><th>ID</th><th>Name</th><th>Email</th><th>Courses</th></tr>`;
for(const s of students){
html += `<tr><td>${s.id}</td><td>${s.name}</td><td>${s.email}</td><td>${[...s.courses].join(', ')}</td></tr>`;
}
document.getElementById('courseResults').innerHTML = html + '</table>';
}


async function suggestMatches(){
const id = document.getElementById('m-student').value;
const course = document.getElementById('m-course').value.trim();
const res = await fetch(api(`/match?studentId=${id}&course=${encodeURIComponent(course)}`));
const matches = await res.json();
let html = '';
for(const m of matches){
html += `<div class='card'><strong>${m.student.name}</strong> (ID ${m.student.id}) — overlaps: ${m.overlapCount}<br/>`;
for(const o of m.overlaps){
html += `<code>${o.day} ${o.start}–${o.end}</code>\n`;
}
html += '</div>';
}
document.getElementById('matchResults').innerHTML = html || '<em>No matches yet.</em>';
}


async function createSession(){
const course = document.getElementById('s-course').value.trim();
const requesterId = document.getElementById('s-requester').value;
const inviteeId = document.getElementById('s-invitee').value;
const start = document.getElementById('s-start').value;
const end = document.getElementById('s-end').value;
const url = api(`/sessions?course=${encodeURIComponent(course)}&requesterId=${requesterId}&inviteeId=${inviteeId}&start=${start}&end=${end}`);
const res = await fetch(url,{method:'POST'});
document.getElementById('sessionOut').textContent = JSON.stringify(await res.json(), null, 2);
}


async function listSessions(){
const me = document.getElementById('s-me').value;
const res = await fetch(api(`/sessions?studentId=${me}`));
const data = await res.json();
let html = '<table><tr><th>ID</th><th>Course</th><th>Who</th><th>Start</th><th>End</th><th>Status</th><th>Actions</th></tr>';
for(const s of data){
html += `<tr><td>${s.id}</td><td>${s.courseCode}</td><td>${s.requester.name} → ${s.invitee.name}</td><td>${s.startDateTime.replace('T',' ')}</td><td>${s.endDateTime.replace('T',' ')}</td><td>${s.status}</td>`+
`<td><button onclick="respond(${s.id},'ACCEPTED')">Accept</button> <button onclick="respond(${s.id},'DECLINED')">Decline</button></td></tr>`;
}
document.getElementById('mySessions').innerHTML = html + '</table>';
}


async function respond(id, status){
await fetch(api(`/sessions/${id}/respond?status=${status}`),{method:'POST'});
listSessions();
}