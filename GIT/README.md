# Lab: Parallel Feature Development

## Overview

In this lab, your team of 4 will simulate a real-world development scenario where multiple developers work on different features simultaneously, then integrate their work together using Pull Requests.

---

## Scenario

Your team is building a simple **Team Profile Page**. Each team member will develop a different feature on their own branch, then create a Pull Request to merge into `main`.

---

## Setup

### Step 1: One person creates the repository using the provided files

**Person A** (Repository Owner):

Create a remote repository, clone it, navigate into it and copy the three files that are in `team_profile_base.zip` .

After copying, your repository folder should look like this:

```
your-repo/
├── index.html
├── styles.css
└── script.js
```

Stage all files and commit with the message "Initial project setup with base structure".

Add your teammates as collaborators on this project.

### Step 2: Everyone clones the repository

**All team members:** Clone the repository and navigate into the project folder.

---

## Feature Assignments

Each person takes ONE feature:

| Person | Branch Name | Feature | Files you will Modify |
|--------|-------------|---------|-----------------|
| **A** | `feature/team-members` | Add team member cards | `index.html`, `styles.css` |
| **B** | `feature/skills-section` | Add skills/technologies section | `index.html`, `styles.css` |
| **C** | `feature/projects-section` | Add projects showcase | `index.html`, `styles.css`, `script.js` |
| **D** | `feature/contact-form` | Add contact form | `index.html`, `styles.css`, `script.js` |

---

## Development Phase

### Person A: Team Members Feature

Create and switch to a new branch called `feature/team-members`.

**Edit `index.html`** — Replace the `#members` section:

```html
<section id="members">
    <h2>Meet the Team</h2>
    <div class="members-grid">
        <div class="member-card">
            <div class="member-avatar">👤</div>
            <h3>Member 1</h3>
            <p class="role">Developer</p>
            <p class="bio">Passionate about clean code and best practices.</p>
        </div>
        <div class="member-card">
            <div class="member-avatar">👤</div>
            <h3>Member 2</h3>
            <p class="role">Designer</p>
            <p class="bio">Creating beautiful user experiences.</p>
        </div>
        <div class="member-card">
            <div class="member-avatar">👤</div>
            <h3>Member 3</h3>
            <p class="role">Developer</p>
            <p class="bio">Full-stack enthusiast and problem solver.</p>
        </div>
        <div class="member-card">
            <div class="member-avatar">👤</div>
            <h3>Member 4</h3>
            <p class="role">DevOps</p>
            <p class="bio">Automating everything that can be automated.</p>
        </div>
    </div>
</section>
```

**Add to `styles.css` under the "Team Members Styles (Person A)" section:**

```css
.members-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 20px;
    margin-top: 20px;
}

.member-card {
    background: #f9f9f9;
    padding: 20px;
    border-radius: 8px;
    text-align: center;
    transition: transform 0.3s ease;
}

.member-card:hover {
    transform: translateY(-5px);
}

.member-avatar {
    font-size: 48px;
    margin-bottom: 10px;
}

.member-card h3 {
    color: #333;
    margin-bottom: 5px;
}

.member-card .role {
    color: #007bff;
    font-weight: bold;
    margin-bottom: 10px;
}

.member-card .bio {
    color: #666;
    font-size: 14px;
}
```

Stage all changes, commit with the message "Add team members section with cards layout", and push the branch to origin.

---

### Person B: Skills Section Feature

Create and switch to a new branch called `feature/skills-section`.

**Edit `index.html`** — Replace the `#skills` section:

```html
<section id="skills">
    <h2>Our Skills</h2>
    <div class="skills-container">
        <div class="skill-item">
            <span class="skill-name">JavaScript</span>
            <div class="skill-bar">
                <div class="skill-progress" style="width: 90%;"></div>
            </div>
        </div>
        <div class="skill-item">
            <span class="skill-name">Python</span>
            <div class="skill-bar">
                <div class="skill-progress" style="width: 85%;"></div>
            </div>
        </div>
        <div class="skill-item">
            <span class="skill-name">Git</span>
            <div class="skill-bar">
                <div class="skill-progress" style="width: 95%;"></div>
            </div>
        </div>
        <div class="skill-item">
            <span class="skill-name">CSS/Design</span>
            <div class="skill-bar">
                <div class="skill-progress" style="width: 80%;"></div>
            </div>
        </div>
    </div>
</section>
```

**Add to `styles.css` under the "Skills Section Styles (Person B)" section:**

```css
.skills-container {
    margin-top: 20px;
}

.skill-item {
    margin-bottom: 15px;
}

.skill-name {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #333;
}

.skill-bar {
    background: #e0e0e0;
    border-radius: 10px;
    height: 20px;
    overflow: hidden;
}

.skill-progress {
    background: linear-gradient(90deg, #007bff, #00d4ff);
    height: 100%;
    border-radius: 10px;
    transition: width 0.5s ease;
}
```

Stage all changes, commit with the message "Add skills section with progress bars", and push the branch to origin.

---

### Person C: Projects Section Feature

Create and switch to a new branch called `feature/projects-section`.

**Edit `index.html`** — Replace the `#projects` section:

```html
<section id="projects">
    <h2>Our Projects</h2>
    <div class="projects-grid">
        <div class="project-card" data-project="1">
            <h3>🚀 Project Alpha</h3>
            <p>A revolutionary web application for task management.</p>
            <div class="project-tags">
                <span class="tag">React</span>
                <span class="tag">Node.js</span>
            </div>
            <button class="project-btn">View Details</button>
        </div>
        <div class="project-card" data-project="2">
            <h3>📊 Data Dashboard</h3>
            <p>Real-time analytics and visualization platform.</p>
            <div class="project-tags">
                <span class="tag">Python</span>
                <span class="tag">D3.js</span>
            </div>
            <button class="project-btn">View Details</button>
        </div>
        <div class="project-card" data-project="3">
            <h3>🛒 E-Commerce API</h3>
            <p>Scalable REST API for online stores.</p>
            <div class="project-tags">
                <span class="tag">Java</span>
                <span class="tag">Spring Boot</span>
            </div>
            <button class="project-btn">View Details</button>
        </div>
    </div>
</section>
```

**Add to `styles.css` under the "Projects Section Styles (Person C)" section:**

```css
.projects-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
    gap: 20px;
    margin-top: 20px;
}

.project-card {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    padding: 25px;
    border-radius: 12px;
    transition: transform 0.3s ease;
}

.project-card:hover {
    transform: scale(1.02);
}

.project-card h3 {
    margin-bottom: 10px;
}

.project-tags {
    margin: 15px 0;
}

.tag {
    background: rgba(255,255,255,0.2);
    padding: 4px 10px;
    border-radius: 15px;
    font-size: 12px;
    margin-right: 5px;
}

.project-btn {
    background: white;
    color: #764ba2;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    font-weight: bold;
    transition: opacity 0.3s;
}

.project-btn:hover {
    opacity: 0.9;
}
```

**Update the `initProjects()` function in `script.js`:**

```javascript
function initProjects() {
    const projectButtons = document.querySelectorAll('.project-btn');
    
    projectButtons.forEach(button => {
        button.addEventListener('click', function() {
            const card = this.closest('.project-card');
            const projectId = card.dataset.project;
            alert('Opening details for Project ' + projectId + '...\n\nThis would navigate to the project page in a real application.');
        });
    });
}
```

Stage all changes, commit with the message "Add projects showcase section with interactivity", and push the branch to origin.

---

### Person D: Contact Form Feature

Create and switch to a new branch called `feature/contact-form`.

**Edit `index.html`** — Replace the `#contact` section:

```html
<section id="contact">
    <h2>Contact Us</h2>
    <form id="contact-form" class="contact-form">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" required placeholder="Your name">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" required placeholder="your@email.com">
        </div>
        <div class="form-group">
            <label for="subject">Subject</label>
            <select id="subject" name="subject" required>
                <option value="">Select a subject</option>
                <option value="general">General Inquiry</option>
                <option value="project">Project Collaboration</option>
                <option value="feedback">Feedback</option>
            </select>
        </div>
        <div class="form-group">
            <label for="message">Message</label>
            <textarea id="message" name="message" rows="5" required placeholder="Your message..."></textarea>
        </div>
        <button type="submit" class="submit-btn">Send Message</button>
    </form>
    <div id="form-status" class="form-status"></div>
</section>
```

**Add to `styles.css` under the "Contact Form Styles (Person D)" section:**

```css
.contact-form {
    max-width: 500px;
    margin: 20px auto 0;
}

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #333;
}

.form-group input,
.form-group select,
.form-group textarea {
    width: 100%;
    padding: 12px;
    border: 2px solid #ddd;
    border-radius: 6px;
    font-size: 16px;
    transition: border-color 0.3s;
}

.form-group input:focus,
.form-group select:focus,
.form-group textarea:focus {
    outline: none;
    border-color: #007bff;
}

.submit-btn {
    background: #28a745;
    color: white;
    border: none;
    padding: 15px 30px;
    font-size: 16px;
    border-radius: 6px;
    cursor: pointer;
    width: 100%;
    transition: background 0.3s;
}

.submit-btn:hover {
    background: #218838;
}

.form-status {
    text-align: center;
    margin-top: 15px;
    padding: 10px;
    border-radius: 6px;
}

.form-status.success {
    background: #d4edda;
    color: #155724;
}

.form-status.error {
    background: #f8d7da;
    color: #721c24;
}
```

**Update the `initContactForm()` function in `script.js`:**

```javascript
function initContactForm() {
    const form = document.getElementById('contact-form');
    const status = document.getElementById('form-status');
    
    if (form) {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Get form data
            const formData = new FormData(form);
            const data = Object.fromEntries(formData);
            
            // Simulate form submission
            status.textContent = 'Sending message...';
            status.className = 'form-status';
            
            setTimeout(() => {
                console.log('Form submitted:', data);
                status.textContent = '✓ Message sent successfully! We will get back to you soon.';
                status.className = 'form-status success';
                form.reset();
            }, 1500);
        });
    }
}
```

Stage all changes, commit with the message "Add contact form with validation and submission handling", and push the branch to origin.

---

## Pull Request Phase

Now each person will create a Pull Request on Bitbucket or GitHub to merge their feature into `main`.

### Step 1: Everyone creates a Pull Request

Each team member:

1. Go to your repository on Bitbucket or GitHub
2. Click **Create pull request** (or find it under **Pull requests** in the left menu)
3. Set:
   - **Source branch:** your feature branch (e.g., `feature/team-members`)
   - **Destination branch:** `main`
4. Add a title describing your feature (e.g., "Add team members section")
5. Add a description explaining what you changed
6. Click **Create pull request**

You should now have 4 open Pull Requests.

### Step 2: Review each other's code

Before merging, review each other's PRs:

1. Open a teammate's Pull Request
2. Go to the **Diff** tab to see the changes
3. Add comments or suggestions if you spot issues
4. Click **Approve** when the code looks good

**Goal:** Each PR should have at least one approval from a teammate before merging.

### Step 3: Merge Pull Requests one by one

**Recommended order:** A → B → C → D

#### Person A merges first:

1. Open your Pull Request on Bitbucket or GitHub
2. Verify you have at least one approval
3. Click **Merge**
4. Choose merge strategy (use "Merge commit" for this lab)
5. Confirm the merge

#### Person B merges second:

1. Open your Pull Request on Bitbucket or GitHub
2. If Bitbucket or GitHub shows conflicts or your branch is behind, update your branch:
   - Locally: switch to `main`, pull, switch to your feature branch, merge `main` into it, push
   - Or use Bitbucket or GitHub's "Update branch" button if available
3. Once the PR shows no conflicts, click **Merge**

#### Person C merges third:

1. Open your Pull Request on Bitbucket or GitHub
2. Update your branch if needed (same process as Person B)
3. Merge when ready

#### Person D merges last:

1. Open your Pull Request on Bitbucket or GitHub
2. Update your branch if needed
3. Merge when ready

> **Note:** Since we're using Pull Requests, Person D doesn't need to do any local merging. Bitbucket or GitHub handles everything on the server side. This is one of the key benefits of the PR workflow — you never merge directly on your machine, reducing the risk of mistakes and keeping `main` clean.

---

## Updating Your Branch (if needed)

If Bitbucket or GitHub shows your PR has conflicts or is "behind" main:

**Option 1: From the command line**

```bash
git switch main
git pull origin main
git switch feature/your-branch
git merge main
# Resolve any conflicts if they appear
git push origin feature/your-branch
```

**Option 2: From Bitbucket or GitHub**

Some Bitbucket or GitHub versions have an "Update branch" or "Sync" button directly in the PR interface.

---

## Verification

After all PRs are merged:

1. Everyone pulls the final `main` branch:
   ```bash
   git switch main
   git pull origin main
   ```

2. Open `index.html` in a browser

3. Verify all features work:
   - [ ] Team member cards display correctly
   - [ ] Skills progress bars are visible
   - [ ] Project cards show and buttons are clickable
   - [ ] Contact form submits with validation

4. View the commit history on Bitbucket or GitHub to see how all branches merged together

---

## Troubleshooting

### I committed on `main` instead of my feature branch

If you accidentally committed on `main` instead of creating a feature branch:

```bash
# 1. Create the feature branch from your current position (keeps your commits)
git branch feature/my-feature

# 2. Reset main back to match origin
git reset --hard origin/main

# 3. Switch to your feature branch and continue working
git switch feature/my-feature
```

### I pushed commits to `main` by mistake

If you already pushed to `main` and need to undo it:

```bash
# 1. Find the commit hash where main should be (before your mistake)
git log --oneline

# 2. Force push main back to that commit (e.g., 53ea5e6)
git push -f origin 53ea5e6:main

# 3. Your commits are still in your local branch, move them to a feature branch
git switch -c feature/my-feature
git push -u origin feature/my-feature
```

> ⚠️ **Warning:** Force pushing rewrites history. Only do this if no one else has pulled your changes yet. Communicate with your team first!

### I committed on the wrong feature branch

If you committed on `feature/team-members` but meant to commit on `feature/skills-section`:

```bash
# 1. Note the commit hash(es) you want to move
git log --oneline

# 2. Switch to the correct branch
git switch feature/skills-section

# 3. Cherry-pick the commit(s) you need (e.g., abc1234)
git cherry-pick abc1234

# 4. Go back to the wrong branch and remove the commit
git switch feature/team-members
git reset --hard HEAD~1   # Removes last commit (use ~2 for last 2, etc.)
```

### I need to undo my last commit (but keep the changes)

```bash
# Undo commit but keep changes staged
git reset --soft HEAD~1

# Undo commit and unstage changes (keep in working directory)
git reset HEAD~1

# Undo commit and discard all changes (careful!)
git reset --hard HEAD~1
```

### My branch is out of sync with origin

If your local branch is behind or diverged from origin:

```bash
# See the difference
git status
git log --oneline --all --graph

# Option 1: Pull and merge (safe)
git pull origin feature/my-feature

# Option 2: Pull and rebase (cleaner history)
git pull --rebase origin feature/my-feature

# Option 3: Force local to match origin (discards local changes!)
git reset --hard origin/feature/my-feature
```

### Bitbucket or GitHub says my PR has conflicts

```bash
# 1. Update your local main
git switch main
git pull origin main

# 2. Switch to your feature branch and merge main into it
git switch feature/my-feature
git merge main

# 3. Resolve conflicts in your editor, then:
git add .
git commit -m "Merge main into feature branch"

# 4. Push the updated branch
git push origin feature/my-feature

# 5. Refresh your PR on Bitbucket or GitHub — conflicts should be resolved
```

### I want to see what changed between branches

```bash
# See commits that are in your branch but not in main
git log main..feature/my-feature --oneline

# See the actual code differences
git diff main..feature/my-feature

# See which files changed
git diff main..feature/my-feature --name-only
```

### I accidentally deleted a branch

```bash
# If you just deleted it, find the commit hash in reflog
git reflog

# Recreate the branch from that commit (e.g., abc1234)
git branch feature/my-feature abc1234
```

---

## Discussion Questions

After completing the lab, discuss as a team:

1. **How did the PR workflow differ from direct merging?** What are the advantages?

2. **How did code review help?** Did you catch any issues before merging?

3. **What happened when PRs needed updating?** Was it clear how to resolve it?

4. **In a real project, what PR rules would you set?** (Required approvals? CI checks? Branch protection?)

---

## Bonus Challenges

If you finish early, try these:

1. **Revert a merged PR:** Use Bitbucket or GitHub's "Revert" button on a merged PR, then re-merge the feature.

2. **Set up branch permissions:** Configure the repository so `main` requires at least one approval before merging.

3. **Create a release tag:** Tag the current commit as `v1.0.0` with the message "First complete team profile page" and push the tag to origin.

4. **Add a new feature together:** Create a footer with social links using the PR workflow — see how it feels now that you've practiced.

---

## Key Takeaways

✅ **Pull Requests enable code review** — Catch issues before they reach main  
✅ **Keep PRs small and focused** — Easier to review and merge  
✅ **Update your branch before merging** — Prevents conflicts in main  
✅ **Communicate with your team** — "My PR is ready for review!"  
✅ **Approve thoughtfully** — Review is a chance to learn and improve code quality
