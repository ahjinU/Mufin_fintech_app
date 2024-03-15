import type { Meta, StoryObj } from '@storybook/react';

import Accordion from './Accordion';

const meta = {
  title: 'Common/Accordion',
  component: Accordion,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description: 'NORMAL: 정상 상태,\n EXCEPTIONAL: 예외 상태,\n COMPLETED: 완료 상태',
    },
    name: {
      description: '아이 이름',
    },
    title: {
      description: '아코디언을 펼치기 전 아이가 어떤 상태인지 표시',
    },
    contents: {
      description: '아코디언을 펼친 후 보여질 내용(내용과 값이 들어간 2차원 배열)',
    },
  },
} satisfies Meta<typeof Accordion>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Normal: Story = {
  args: {
    mode: 'NORMAL',
    name: '김지니',
    title: '는 순조롭게 적금을 드는 중...',
    contents: [
      ['적금 가입일', '2024-01-02'],
      ['적금 만기일', '2024-07-02'],
      ['남은 달 수', '4달'],
      ['현재까지 이체금액', '60,000원'],
      ['적금 이자율', '0.05%'],
    ],
  },
};

export const Exceptional: Story = {
  args: {
    mode: 'EXCEPTIONAL',
    name: '김민니',
    title: '는 상환이 두 번이나 밀렸슈',
    contents: [
      ['가입일', '2024-01-02'],
      ['모르겠어', '뭐넣지'],
    ],
  },
};

export const Completed: Story = {
  args: {
    mode: 'COMPLETED',
    name: '김하니',
    title: '는 적금이 만기되어 이자를 받았어요!',
    contents: [
      ['적금 가입일', '2023-06-02'],
      ['적금 만기일', '2024-01-02'],
      ['총 이체금액', '150,000원'],
      ['적금 이자율', '0.1%'],
      ['이자', '1,500원'],
      ['총 이체금액 + 이자', '151,500원'],
    ],
  },
};
