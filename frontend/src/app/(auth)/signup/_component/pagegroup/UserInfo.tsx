import { useEffect, useState } from 'react';
import { ComplexInput, Input, Button } from '@/components';
import {
  isValidName,
  isValidYear,
  isValidMonth,
  isValidDay,
} from '../../_utils/validator';
import DateInput from './DateInput';

export default function UserInfo({ onNext }: { onNext: (data: any) => void }) {
  const [info, setInfo] = useState({ name: '', year: '', month: '', day: '' });
  const [gender, setGender] = useState('');
  const [message, setMessage] = useState({ nameMessage: '', birthMessage: '' });
  const [buttonMode, setButtonMode] = useState<'ACTIVE' | 'NON_ACTIVE'>(
    'NON_ACTIVE',
  );

  const onChangeInput = (e: { target: { name: string; value: string } }) => {
    const { name, value } = e.target;
    setInfo({ ...info, [name]: value });

    if (name === 'name') {
      setMessage({
        ...message,
        nameMessage: isValidName(value)
          ? ''
          : '이름은 2자 이상 6자 이하의 문자로 입력해주세요!',
      });
    } else if (name === 'year' || name === 'month' || name === 'day') {
      setMessage({
        ...message,
        birthMessage:
          isValidYear(info.year) &&
          isValidMonth(info.month) &&
          isValidDay(info.day)
            ? ''
            : '올바른 날짜를 입력해주세요!',
      });
    }
  };

  useEffect(() => {
    if (
      isValidName(info.name) &&
      isValidYear(info.year) &&
      isValidMonth(info.month) &&
      isValidDay(info.day) &&
      gender !== ''
    ) {
      setButtonMode('ACTIVE');
    } else {
      setButtonMode('NON_ACTIVE');
    }
  }, [info, gender]);

  const handleNext = () => {
    if (buttonMode == 'ACTIVE') {
      const data = {
        name: info.name,
        gender: gender,
        birth: info.year + '-' + info.month + '-' + info.day,
      };
      onNext(data);
    }
  };

  return (
    <div className="flex flex-col gap-[2rem]">
      <ComplexInput
        label="이름"
        mode="ERROR"
        isMsg
        message={message.nameMessage}
      >
        <Input
          placeholder="이름을 입력해주세요"
          name="name"
          type="text"
          onChange={onChangeInput}
        />
      </ComplexInput>
      <ComplexInput label="성별" mode="NONE">
        <div className="flex gap-[0.8rem]">
          <Button
            label="남성"
            mode={gender === '남' ? 'SELECTED' : 'NOT_SELECTED'}
            onClick={() => setGender('남')}
          />
          <Button
            label="여성"
            mode={gender === '여' ? 'SELECTED' : 'NOT_SELECTED'}
            onClick={() => setGender('여')}
          />
        </div>
      </ComplexInput>
      <ComplexInput
        label="생년월일"
        mode="ERROR"
        isMsg
        message={message.birthMessage}
      >
        <div className="flex justify-between items-center">
          <DateInput name="year" onChange={onChangeInput} />
          <DateInput name="month" onChange={onChangeInput} />
          <DateInput name="day" onChange={onChangeInput} />
        </div>
      </ComplexInput>
      <div className="fixed bottom-0 inset-x-0 px-[1.2rem] py-[3rem]">
        <Button label="다음" mode={buttonMode} onClick={handleNext} />
      </div>
    </div>
  );
}
