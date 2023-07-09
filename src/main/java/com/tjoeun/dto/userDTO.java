package com.tjoeun.dto;

public class userDTO {
	
	private int idx;
	private String num;
	private String id;
	private String password;
	private String position;
	private String name;
	private String phone;
	private String email;
	private int currentPage;
	

	public userDTO(int idx, String num, String id, String password, String position, String name, String phone,
			String email, int currentPage) {
		super();
		this.idx = idx;
		this.num = num;
		this.id = id;
		this.password = password;
		this.position = position;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.currentPage = currentPage;
	}

	public userDTO() {
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	

	@Override
	public String toString() {
		return "userDTO [idx=" + idx + ", num=" + num + ", id=" + id + ", password=" + password + ", position="
				+ position + ", name=" + name + ", phone=" + phone + ", email=" + email + ", currentPage=" + currentPage
				+ "]";
	}

	public String queryString() {
        StringBuilder sb = new StringBuilder();

        if (num != null) {
            sb.append("&num=" + num);
        }
        if (id != null) {
        	sb.append("&size=" + id);
        }
        if (position != null) {
            sb.append("&capacity=" + position);
        }
        if (name != null) {
            sb.append("&price=" + name);
        }


        return sb.toString();
    }
	
	
}
