package frame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame {

    public Login() {
        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null);

        // 메인 패널
        JPanel p = new JPanel();

        // ID 입력 패널
        JPanel p1 = new JPanel();
        JLabel j1 = new JLabel("ID");
        JTextField t1 = new JTextField(10);
        p1.add(j1);
        p1.add(t1);

        // 비밀번호 입력 패널
        JPanel p2 = new JPanel();
        JLabel j2 = new JLabel("Password");
        JPasswordField t2 = new JPasswordField(10);
        p2.add(j2);
        p2.add(t2);

        // 버튼
        JPanel p3 = new JPanel();
        JButton b1 = new JButton("Sign In");

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = t1.getText();
                String password = new String(t2.getPassword());
                String location = "C:\\Swing\\output.txt";

                boolean login = false; // 로그인 성공 여부

                // 파일에서 사용자 정보 읽기
                try  {
                    FileReader fileReader = new FileReader(location);
                    BufferedReader reader = new BufferedReader(fileReader);
                    String line = reader.readLine(); // 파일에서 한 줄씩 읽기
                    if (line != null) {
                        String[] part = line.split(","); // ","를 기준으로 ID와 비밀번호 분리
                        String Id = part[0];
                        String Password = part[1];

                        // 입력한 ID와 비밀번호가 파일에 저장된 ID와 비밀번호와 일치하는지 확인
                        if (id.equals(Id) && password.equals(Password)) {
                            login = true; // 일치하면 로그인 성공
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // 로그인 성공 시 처리
                if (login) {
                    JOptionPane.showMessageDialog(null, "로그인 성공");
                    dispose(); // 현재 로그인 창 닫기
                    new Index(); // Index 창 열기 (로그인 후 화면)
                } else {
                    JOptionPane.showMessageDialog(null, "로그인 실패");
                }
            }
        });

        // 회원가입 버튼 생성
        JButton b2 = new JButton("Sign Up"); // 회원가입 버튼

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignUp();
            }
        });

        p3.add(b1); // 로그인 버튼을 버튼 패널에 추가
        p3.add(b2); // 회원가입 버튼을 버튼 패널에 추가

        // 모든 패널들을 메인 패널에 추가
        p.add(p1);
        p.add(p2);
        p.add(p3);

        add(p);

        setVisible(true); // 프레임을 보이도록 설정 (맨 마지막에 호출)
    }

    // 프로그램 실행 시 로그인 창을 띄움
    public static void main(String[] args) {
        new Login(); // Login 클래스의 생성자 호출로 GUI 창 생성
    }
}
