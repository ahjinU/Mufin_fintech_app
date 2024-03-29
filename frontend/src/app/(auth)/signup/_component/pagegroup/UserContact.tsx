import { useState } from 'react';
import { ComplexInput, Input, Button, TinyButton } from '@/components';
import { checkTelephoneParent, checkTelephoneChild } from '../../_apis/apis';

export default function UserContact({
  onNext,
}: {
  onNext: (data: any) => void;
}) {
  const [contact, setContact] = useState({
    telephone: '',
    address: '',
    address2: '',
  });
  const [isValid, setIsValid] = useState(false);
  const [message, setMessage] = useState('');

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    setContact({ ...contact, [e.target.name]: e.target.value });
  };

  const checkTelephone = async () => {
    try {
      const fetchedData = await checkTelephoneParent(contact.telephone);
      if (fetchedData.ok) {
        setIsValid(true);
        setMessage('사용 가능한 번호입니다😀');
        console.log(fetchedData.headers.getSetCookie)
      } else {
        setIsValid(false);
        setMessage('중복된 번호입니다😢');
        console.log(fetchedData);
      }
    } catch (error) {
      console.error('전화번호 중복 검사 에러', error);
    }
  };

  const handleNext = () => {
    onNext(contact);
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput
        label="전화번호"
        mode={isValid ? 'INFORM' : 'ERROR'}
        isMsg
        message={message}
      >
        <div className="flex items-center gap-[1rem]">
          <Input
            placeholder="전화번호를 입력해주세요"
            name="telephone"
            onChange={onChangeInput}
          />
          <TinyButton label="중복 확인" onClick={checkTelephone} />
        </div>
      </ComplexInput>
      <ComplexInput label="주소" mode="NONE">
        <div className="flex flex-col gap-[0.6rem]">
          <div className="flex items-center gap-[1rem]">
            <Input
              placeholder="주소를 입력해주세요"
              name="address"
              onChange={onChangeInput}
            />
            <TinyButton label="주소 찾기" onClick={() => {}} />
          </div>
          <Input
            placeholder="상세 주소를 입력해주세요"
            name="address2"
            onChange={onChangeInput}
          />
        </div>
      </ComplexInput>
      <div className="fixed bottom-0 left-[1.2rem] right-[1.2rem] my-[1.2rem]">
        <Button label="다음" mode="ACTIVE" onClick={handleNext} />
      </div>
    </div>
  );
}
