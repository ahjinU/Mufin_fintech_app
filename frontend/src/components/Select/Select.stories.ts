import type { Meta, StoryObj } from '@storybook/react';

import Select from './Select';

const meta = {
  title: 'Common/Select',
  component: Select,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description:
        '대출 신청(LOAN), 대출 상환(LOAN_RETURN), 대출 리포트(LOAN_REPORT), 적금 신청(SAVINGS), 적금 납부(SAVINGS_RETURN) 각각에 따라 다른 label 텍스트와 select 요소를 보여줍니다.',
    },
    min: {
      description: '지정할 가장 작은 숫자입니다.',
    },
    max: {
      description: '지정할 가장 높은 숫자입니다.',
    },
    initialValue: {
      description:
        'prop으로 전달하는 것은 옵션인데, 대출 리포트처럼 이미 정해진 값을 확인할 때만 사용합니다.',
    },
  },
} satisfies Meta<typeof Select>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Loan: Story = {
  args: {
    mode: 'LOAN',
    min: 1,
    max: 28,
  },
};

export const LoanReturn: Story = {
  args: {
    mode: 'LOAN_RETURN',
    min: 1,
    max: 3,
  },
};

export const LoanReport: Story = {
  args: {
    mode: 'LOAN_REPORT',
    min: 1,
    max: 28,
    initialValue: 28,
  },
};

export const Savings: Story = {
  args: {
    mode: 'SAVINGS',
    min: 1,
    max: 28,
  },
};

export const SavingsReturn: Story = {
  args: {
    mode: 'LOAN_RETURN',
    min: 1,
    max: 3,
  },
};
